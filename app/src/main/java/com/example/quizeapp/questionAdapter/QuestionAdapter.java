package com.example.quizeapp.questionAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.renderscript.ScriptIntrinsic;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizeapp.R;
import com.example.quizeapp.models.Question;
import com.example.quizeapp.models.Result;
import com.example.quizeapp.questionActivity.QuestionActivity;
import com.example.quizeapp.quizAdapter.QuizAdapter;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    Context context;
    Question question;
    List<String> options;



    public QuestionAdapter (QuestionActivity context, Question question) {
        this.context=context;
        this.question=question;
        options=List.of(question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
        return;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.question_option,parent,false);

        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position)  {
        holder.textView.setText(options.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(context,options.get(position), Toast.LENGTH_SHORT).show();
                question.setUseranswer(options.get(position));

                 notifyDataSetChanged();
            }


        }
        );

        if(holder.itemView.isClickable()){

            if(question.getUseranswer(options.get(position)) == options.get(position)){
                holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg);
                Toast.makeText(context, options.get(position), Toast.LENGTH_SHORT).show();


            }
            else {
                holder.itemView.setBackgroundResource(R.drawable.option_item_bg);

            }

        }








    }




    @Override
    public int getItemCount() {
        return options.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout linearLayout;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout= itemView.<LinearLayout>findViewById(R.id.optionContainer_id);
            textView= itemView.<TextView>findViewById(R.id.questioOptionTv_id);


        }
    }

}
