package com.example.regional_information.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.regional_information.RegionInfo;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM RegionInfo")
    List<RegionInfo> getAll();

    @Query("SELECT * FROM RegionInfo WHERE :id = id")
    List<RegionInfo> getRegionByID(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RegionInfo ... regionsInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RegionInfo regionInfo);

    @Delete
    void delete(RegionInfo regionInfo);

    @Query("DELETE FROM regioninfo where id>=0")
    void deleteAll();

}
