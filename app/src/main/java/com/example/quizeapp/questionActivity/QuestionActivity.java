package com.example.quizeapp.questionActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizeapp.R;
import com.example.quizeapp.models.Question;
import com.example.quizeapp.models.Quiz;
import com.example.quizeapp.models.Result;
import com.example.quizeapp.questionAdapter.QuestionAdapter;
import com.example.quizeapp.resultActivity.ResultActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

public  class QuestionActivity extends AppCompatActivity {

    List<Quiz> quizzes=null;
   // ArrayMap<String, Question> questions=null;
    Map<String,Question> questions=null;

    int index = 1 ;
    int time = 30 ;
    private  Boolean stoptimer = false;




    TextView timer;
    TextView quesDescription;
    Button previous_btn;
    Button next_btn;
    Button submit_btn;

    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    LinearLayout linearLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        timer= this.<TextView>findViewById(R.id.timer_id);
        quesDescription= this.<TextView>findViewById(R.id.description_id);
        recyclerView= this.<RecyclerView>findViewById(R.id.questionOptionRcylList_id);
        next_btn= this.<Button>findViewById(R.id.next_id);
        submit_btn= this.<Button>findViewById(R.id.submit_id);
        previous_btn= this.<Button>findViewById(R.id.previous_id);


        setUpFireStore();





    }

    private final void setTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                timer.setText(String.valueOf("timer : " + time + "s"));
                time--;
                if(time < 0){
                    Intent intent = new Intent(QuestionActivity.this,ResultActivity.class);

                    Gson gson = new Gson();
                    String json = gson.toJson(quizzes.get(0));
                    // or // String json = gson.toJson(quizzes != null ? (Quiz)quizzes.get(0) : null);

                    intent.putExtra("QUIZ", json);
                    startActivity(intent);
                    stoptimer = true ;
                }
                else if(stoptimer == false){
                    handler.postDelayed(this,1000);

                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stoptimer = false ;
        finish();
    }

    private void setUpFireStore() {
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        String date = getIntent().getStringExtra("DATE");


        if(date != null){
            firebaseFirestore.collection("quizzes").whereEqualTo("title",date)
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    //Log.d("DATA",queryDocumentSnapshots.toObjects(Quiz.class).toString());
                    if(queryDocumentSnapshots!=null && !queryDocumentSnapshots.isEmpty()){
                        quizzes=queryDocumentSnapshots.toObjects(Quiz.class);
                        //int i=0;
                        questions =quizzes.get(0).getQuestions();
                        //questions =quizzes.get(0).setQuestions(questions);


                        bindView();
                        setTimer();

                    }
                    else {
                        Toast.makeText(QuestionActivity.this, "empty", Toast.LENGTH_SHORT).show();
                        finish();
                    }



                }

            });
                    }


    }



    private void bindView() {



           next_btn.setVisibility(View.GONE);
           previous_btn.setVisibility(View.GONE);
           submit_btn.setVisibility(View.GONE);



           if(index==1)//first question
           {
               Question question=questions.get(String.format("question" + this.index));

               quesDescription.setText(question.getDescription());
               questionAdapter= new QuestionAdapter(this,question);
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
               recyclerView.setAdapter(questionAdapter);
               recyclerView.setHasFixedSize(true);

               next_btn.setVisibility(View.VISIBLE);


               next_btn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       index++;
                       bindView();
                   }

               });


           }
           else if(index==questions.size())//last question
           {
               Question question=questions.get(String.format("question" + this.index));


               quesDescription.setText(question.getDescription());
               questionAdapter= new QuestionAdapter(this,question);
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
               recyclerView.setAdapter(questionAdapter);
               recyclerView.setHasFixedSize(true);

               previous_btn.setVisibility(View.VISIBLE);
               submit_btn.setVisibility(View.VISIBLE);

               submit_btn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Log.d("FINALQUIZ",questions.toString());

                       Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                       Gson gson = new Gson();

                       String json = gson.toJson(quizzes.get(0));
                       // or // String json = gson.toJson(quizzes != null ? (Quiz)quizzes.get(0) : null);

                       intent.putExtra("QUIZ", json);
                        startActivity(intent);


                   }
               });

           }
           else{
               Question question=questions.get(String.format("question" + this.index));


               quesDescription.setText(question.getDescription());
               questionAdapter= new QuestionAdapter(this,question);
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
               recyclerView.setAdapter(questionAdapter);
               recyclerView.setHasFixedSize(true);

               previous_btn.setVisibility(View.VISIBLE);
               next_btn.setVisibility(View.VISIBLE);

               previous_btn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       index--;
                       bindView();
                   }
               });

           }


           //  HashMap question=questions.get(index);
        /* Question question=new Question("Choose your country name .....","PAK","UK",
                "US","IND","IND");*/









    }

}