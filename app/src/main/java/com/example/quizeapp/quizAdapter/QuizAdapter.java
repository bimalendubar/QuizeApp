package com.example.quizeapp.quizAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizeapp.MainActivity;
import com.example.quizeapp.R;
import com.example.quizeapp.models.Quiz;
import com.example.quizeapp.questionActivity.QuestionActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>

{

  private Context context;
  private List<Quiz> quizzes;

    public QuizAdapter(Context context, List quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.quiz_item,parent,false);

        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {


          Quiz quiz= quizzes.get(position);
          holder.textView.setText(quiz.getTitle());

          ColorPicker(holder,position);
          IconPicker(holder,position);


          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, quizzes.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context,QuestionActivity.class);
                intent.putExtra("DATE",quizzes.get(position).getTitle());
                context.startActivity(intent);



            }
        });



    }

    private void IconPicker(QuizViewHolder holder, int position) {
        int icons[]={R.drawable.ic_icon_1ed,R.drawable.ic_icon_checklist,R.drawable.ic_icon_education,
                R.drawable.ic_icon_event_date_calendar,R.drawable.ic_icon_flipchart_presentation,
                R.drawable.ic_icon_hat_graduation_cap, R.drawable.ic_icon_idea_lightbulb,R.drawable.ic_icon_laboratory_research_beaker,
                R.drawable.ic_icon_mail_envelope,R.drawable.ic_icon_mind_brain,R.drawable.ic_icon_money_purse,
                R.drawable.ic_icon_phone_mobile,R.drawable.ic_icon_pointer_location,R.drawable.ic_icon_price_discount,
                R.drawable.ic_icon_person,R.drawable.ic_icon_record_speaker,R.drawable.ic_icon_sunglasses_face,
                R.drawable.ic_icon_teacher_education_book,R.drawable.ic_icon_timer_clock,R.drawable.ic_icon_trophy_award_cup};

               int icon=position %  icons.length;
               holder.imageView.setImageResource(icons[icon]);
    }

    private void ColorPicker(QuizViewHolder holder, int position) {
        String colors[]={"#FF88737B","#FFE91E63","#FF9C27B0","#FFFFEB3B","#FFF44336"};
        int color=position %  colors.length;
        int intColor= Color.parseColor(colors[color]);
        holder.item_constrain_id.setBackgroundColor(intColor);
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public  class QuizViewHolder extends RecyclerView.ViewHolder{
        LinearLayout item_constrain_id;
        ImageView imageView;
        TextView textView;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.<ImageView>findViewById(R.id.item_img_id);
        textView= itemView.<TextView>findViewById(R.id.item_tv_Id);
        item_constrain_id=itemView.findViewById(R.id.item_constraints_id);
    }
}

}
