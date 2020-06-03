package com.example.helloworld;

import androidx.room.RoomDatabase;

public abstract class AppDatabase extends RoomDatabase {

    public abstract SettingsDAO settingsDAO();
}
