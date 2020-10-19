package com.example.smaexample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TestEntity.class}, version = 1, exportSchema = false)
public abstract class TestDatabase extends RoomDatabase {
    private static final String DB_NAME="TEST_DB";
    private static TestDatabase instance;

    public static synchronized TestDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context, TestDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract TestDAO testDAO();
}
