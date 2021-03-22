package com.example.regional_information.database;



import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.regional_information.RegionInfo;

@Database(entities = {RegionInfo.class}, version = 4)
public abstract class RegionDBForPeriod extends RoomDatabase {
    public abstract DAO RegionDAO();
}
