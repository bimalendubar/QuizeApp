package com.example.quizeapp.utils;

import com.example.quizeapp.quizAdapter.QuizAdapter;

public class ColorsPicker  {
    String colors[]={"#FF88737B","#FFE91E63","#FF9C27B0","#FFFFEB3B","#FFF44336"};
    int currentColor=0;
    public String getColors(){
        currentColor=(currentColor+1) % colors.length;
        return colors[currentColor];


    }

}
