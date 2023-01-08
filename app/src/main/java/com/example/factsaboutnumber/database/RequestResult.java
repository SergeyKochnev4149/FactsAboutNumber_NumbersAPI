package com.example.factsaboutnumber.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RequestResult {

    @PrimaryKey (autoGenerate = true) private int id;
    private String factAboutNumber_db;

    public RequestResult(String factAboutNumber_db) {
        this.factAboutNumber_db = factAboutNumber_db;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactAboutNumber_db() {
        return factAboutNumber_db;
    }

    public void setFactAboutNumber_db(String factAboutNumber_db) {
        this.factAboutNumber_db = factAboutNumber_db;
    }
}
