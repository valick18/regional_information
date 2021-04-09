package com.example.regional_information.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.regional_information.Information;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM Information")
    List<Information> getAll();

    @Query("SELECT * FROM Information WHERE :id = id")
    List<Information> getRegionByID(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Information ... regionsInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Information regionInfo);

    @Delete
    void delete(Information regionInfo);

    @Query("DELETE FROM Information where id>=0")
    void deleteAll();

}
