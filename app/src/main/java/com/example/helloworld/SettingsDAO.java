package com.example.helloworld;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDAO {

    @Query("SELECT * FROM SettingsEntity")
    LiveData<List<SettingsEntity>> getAll();

    @Query("SELECT * FROM SettingsEntity WHERE email IN (:emails)")
    LiveData<List<SettingsEntity>> loadAllByIds(String[] emails);

    @Update
    void updateSettings(SettingsEntity... settingsEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SettingsEntity... settingsEntities);

    @Delete
    void delete(SettingsEntity settingsEntity);
}
