package com.example.finalproject.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomCarDao {

    @Query("SELECT * from saved_cars ORDER BY uid ASC")
    LiveData<List<CustomCar>> getAllCustomCars();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CustomCar customCar);

    @Query("DELETE FROM saved_cars WHERE uid = :savedCarID")
    void deleteOrder(String savedCarID);

    @Query("DELETE FROM saved_cars")
    void deleteAll();
}
