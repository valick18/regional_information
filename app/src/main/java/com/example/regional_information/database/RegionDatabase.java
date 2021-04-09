package com.example.regional_information.database;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.regional_information.Information;

@Database(entities = {Information.class}, version = 5)
public abstract class RegionDatabase extends RoomDatabase {
    public abstract DAO RegionDAO();
}
