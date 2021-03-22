package com.example.regional_information.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MigrationDB {

    private static int version;

    public MigrationDB(int oldVersion){
        this.version = oldVersion;
    }

    public static final Migration migrationToNewVersion = new Migration(version,version+1){

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE RegionInfo ADD COLUMN ___ INTEGER DEFAULT 0 NOT NULL");
        }
    };
}
