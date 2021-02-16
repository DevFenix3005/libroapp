package com.lania.ejercicio2.utils;

import android.content.Context;

import androidx.room.Room;

import com.lania.ejercicio2.dominio.AppDatabase;

public class DatabaseProvider {

    private static AppDatabase appDatabase;


    public static AppDatabase getDatabase(Context context) {
        if (appDatabase != null) return appDatabase;
        else {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "biblioteca")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            return appDatabase;
        }
    }

}
