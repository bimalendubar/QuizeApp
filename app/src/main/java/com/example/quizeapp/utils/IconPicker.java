package com.example.quizeapp.utils;

import com.example.quizeapp.R;
import com.example.quizeapp.quizAdapter.QuizAdapter;

public class IconPicker{
    int Icons[]={R.drawable.ic_icon_1ed,R.drawable.ic_icon_checklist,R.drawable.ic_icon_education,
            R.drawable.ic_icon_event_date_calendar,R.drawable.ic_icon_flipchart_presentation,R.drawable.ic_icon_hat_graduation_cap,
            R.drawable.ic_icon_idea_lightbulb,R.drawable.ic_icon_laboratory_research_beaker,R.drawable.ic_icon_mail_envelope,
            R.drawable.ic_icon_mind_brain,R.drawable.ic_icon_money_purse,
            R.drawable.ic_icon_phone_mobile,R.drawable.ic_icon_pointer_location,R.drawable.ic_icon_price_discount,
            R.drawable.ic_icon_person,R.drawable.ic_icon_record_speaker,R.drawable.ic_icon_sunglasses_face,
            R.drawable.ic_icon_teacher_education_book,R.drawable.ic_icon_timer_clock,R.drawable.ic_icon_trophy_award_cup};
    int currentIcon=0;
   public int getIcons(){
        currentIcon =(currentIcon +1) % Icons.length;
        return Icons[currentIcon];


    }

}
