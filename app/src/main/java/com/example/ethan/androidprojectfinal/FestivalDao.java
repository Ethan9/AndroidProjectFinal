package com.example.ethan.androidprojectfinal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FestivalDao {

    @Insert
    void insert(Festival festival);

    @Query("DELETE FROM festival_table")
    void deleteAll();

    @Query("SELECT * from festival_table ORDER BY festival ASC")
    LiveData<List<Festival>> getAllFestivals();
}
