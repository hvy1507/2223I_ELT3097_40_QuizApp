package com.project.quizapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class   Result {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String date;
    private String result;
    private String monHoc;
    private String level;

    //Getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", result='" + result + '\'' +
                ", monHoc='" + monHoc + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
