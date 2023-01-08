package com.example.factsaboutnumber.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {RequestResult.class}, version = 1)
public abstract class FactsAboutNumber_Database extends RoomDatabase {
    public abstract RequestResultDao getRequestResultDao();

    private static FactsAboutNumber_Database factsAboutNumber_database;

    //Only for app with one thread
    public static FactsAboutNumber_Database getFactsAboutNumber_database(Context context) {
        if (factsAboutNumber_database == null)
            return factsAboutNumber_database = Room.databaseBuilder(context,
                    FactsAboutNumber_Database.class, "FactsAboutNumber_Database").allowMainThreadQueries().build();

        return factsAboutNumber_database;
    }


}
