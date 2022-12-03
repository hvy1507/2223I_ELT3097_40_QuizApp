package com.project.quizapp.DataItem;

public class Category {
    private final String subjectName;
    private final int subjectImage;

    public Category(String name, int image) {
        this.subjectName = name;
        this.subjectImage = image;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getSubjectImage() {
        return subjectImage;
    }

}
