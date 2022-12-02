package com.project.quizapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Question {

    @SerializedName("answer_1")
    @Expose
    private final String answer1;
    @SerializedName("answer_2")
    @Expose
    private final String answer2;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("correct")
    @Expose
    private String correct;

    public Question(String answer1, String answer2) {
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
