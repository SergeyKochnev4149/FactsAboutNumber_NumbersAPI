package com.example.factsaboutnumber.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface RequestResultDao {

    @Query("SELECT * FROM requestresult")
    List<RequestResult> getAll();

    @Insert
    void insertAll(RequestResult ... requestResults);

    @Insert
    void insert(RequestResult requestResult);

    @Query("DELETE FROM requestresult")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM requestresult")
    int getCountOfRecords();

}
