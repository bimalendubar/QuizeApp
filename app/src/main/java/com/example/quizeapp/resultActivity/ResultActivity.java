package com.example.quizeapp.resultActivity;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.jvm.internal.Intrinsics;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.quizeapp.MainActivity;
import com.example.quizeapp.R;
import com.example.quizeapp.models.Question;
import com.example.quizeapp.models.Quiz;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import static android.text.Html.*;

public class ResultActivity extends AppCompatActivity {
    TextView txtResult;
    TextView ansPreview;
    Quiz quiz;
    Map<String,Question> questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResult = this.<TextView>findViewById(R.id.result_id);
        ansPreview = this.<TextView>findViewById(R.id.ansPreview_id);

        String quizData = getIntent().getStringExtra("QUIZ").trim();

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new StringReader(quizData));
        jsonReader.setLenient(true);
        quiz = (Quiz) gson.fromJson(jsonReader,Quiz.class);

        calculateScore();
        setAnsPreview();

       // String i = String.valueOf(3);


    }



    private final void calculateScore() {
        int  score = 0;

        Iterator var3 = quiz.getQuestions().entrySet().iterator();

        while(var3.hasNext()) {

            Entry entry = (Entry)var3.next();
            Question question = (Question)entry.getValue();

            if (Intrinsics.areEqual(question.getAnswer(), question.getUseranswer()))
            {
                score += 10;
            }

        }
 // or  we can use for loop
        /*for (Entry<String, Question> stringQuestionEntry : quiz.getQuestions().entrySet()) {

            Entry entry = (Entry) stringQuestionEntry;
            Question question = (Question) entry.getValue();

            if (Intrinsics.areEqual(question.getAnswer(), question.getUseranswer())) {
                score += 10;
            }

        }*/

        txtResult.setText(String.valueOf("Score:"  + score));

    }

    private final void setAnsPreview() {

        StringBuilder builder = new StringBuilder("");

        Iterator var3 = quiz.getQuestions().entrySet().iterator();

        while(var3.hasNext()) {

            Entry entry = (Entry) var3.next();
            Question question = (Question) entry.getValue();
            builder.append("<font color'#18206F'><b>Question: "        +      question.getDescription()         +        "</b></font><b/><b/>");
            builder.append("<font color'#009688'><b>Answer: "          +       question.getAnswer()             +        "</font><b/><b/>");
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            ansPreview.setText(Html.fromHtml(builder.toString(), FROM_HTML_MODE_COMPACT));

        }
        else {
            ansPreview.setText( Html.fromHtml(builder.toString()));

        }


    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(ResultActivity.this);
        builder.setMessage("Exit this Quiz?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                //finish();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}