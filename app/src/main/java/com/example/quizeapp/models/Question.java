package com.example.quizeapp.models;

import java.util.Objects;

import androidx.annotation.NonNull;

public final  class Question
{
    private String description ="";
    private String option1 ="";
    private String option2 ="";
    private String option3 ="";
    private String option4 ="";
    private String answer="";
    private String useranswer ="";

    public Question(String description, String option1, String option2,
                    String option3, String option4, String answer, String useranswer) {

    }


    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", useranswer='" + useranswer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return getDescription().equals(question.getDescription()) &&
                getOption1().equals(question.getOption1()) &&
                getOption2().equals(question.getOption2()) &&
                getOption3().equals(question.getOption3()) &&
                getOption4().equals(question.getOption4()) &&
                getAnswer().equals(question.getAnswer()) &&
                useranswer.equals(question.useranswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getOption1(), getOption2(), getOption3(), getOption4(), getAnswer(), useranswer);
    }

    public Question() {

    }

    public Question(String description, String option1, String option2, String option3, String option4, String answer) {
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        return;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUseranswer(String s) {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    public Question Question(String description, String option1, String option2,
                             String option3, String option4, String answer, String useranswer) {
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.useranswer = useranswer;

      return new Question(description,option1,option2,option3,option4,answer,useranswer);
    }

    public  String getUseranswer() {
        return useranswer;

    }
}
