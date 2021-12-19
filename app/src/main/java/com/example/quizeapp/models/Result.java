package com.example.quizeapp.models;

import java.util.Objects;

import androidx.annotation.NonNull;

public final class Result {
   private int marks = Integer.parseInt(null);

    public Result() {
        super();
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
        return "Result{" +
                "marks=" + marks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return getMarks() == result.getMarks();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarks());
    }

    public Result(int marks) {
        this.marks = marks;
    }

    public String getMarks() {
        return String.valueOf(marks);
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
