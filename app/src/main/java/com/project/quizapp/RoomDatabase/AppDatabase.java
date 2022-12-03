package com.project.quizapp.RoomDatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.project.quizapp.DataItem.Result;
import com.project.quizapp.Interface.ItemDAO;

// Tạo ROOM Database
@Database(entities = {Result.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDAO getItemDAO();
}