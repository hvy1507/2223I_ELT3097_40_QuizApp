package com.project.quizapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.quizapp.Result;

import java.util.List;
// Thêm các truy vấn SQLite và method getItems
@Dao
public interface ItemDAO {
    @Insert
    void insert(Result... items);

    @Update
    void update(Result... items);

    @Delete
    void delete(Result item);

    @Query("SELECT * FROM items")
    List<Result> getItems();
}
