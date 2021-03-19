package com.example.regional_information.database;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.regional_information.RegionInfo;

@Database(entities = {RegionInfo.class}, version = 2)
public abstract class RegionDBForPeriod extends RoomDatabase {
    public abstract DAO RegionDAO();
}
