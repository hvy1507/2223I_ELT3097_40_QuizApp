package com.project.quizapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

// Tạo ROOM Database
@Database(entities = {Result.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDAO getItemDAO();
}