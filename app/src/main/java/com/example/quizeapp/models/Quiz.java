package com.example.quizeapp.models;


import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;

public final class Quiz  {
    private   String id="";
    private String title ="";
    private Map<String,Question> questions = Map.of();



    public Map<String, Question> getQuestions() {
        return questions;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return getId().equals(quiz.getId()) &&
                getTitle().equals(quiz.getTitle()) &&
                getQuestions().equals(quiz.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getQuestions());
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }

    public Map<String, Question> setQuestions(Map<String, Question> questions) {
        this.questions = questions;
        return questions;
    }

    public Quiz(String id, String title, Map<String, Question> questions) {

    }

    public Quiz Quiz(String id, String title, Map<String, Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
        return new Quiz(id,title,questions);
    }

    // ArrayMap<String,Question> questions =new ArrayMap<>();
    //HashMap<String,Question> questions=new HashMap<>();
    public Quiz() {

    }

    public Quiz(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
