package com.example.regional_information;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.regional_information.database.DAO;
import com.example.regional_information.database.RegionDBForPeriod;
import com.example.regional_information.database.RegionDatabase;
import com.example.regional_information.parserXML.Coordinates;
import com.example.regional_information.parserXML.CoordinatesParser;
import com.example.regional_information.parserXML.RegionParser;
import com.google.android.gms.maps.model.LatLng;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RegionDatabase dbForDay;
    private DAO daoForDay;
    private DAO daoForPeriod;
    private RegionDBForPeriod dbForPeriod;
    private ImageView btMap;

    private List<RegionInfo> list;
    private List<RegionInfo> listForPeriod;
    private static MainActivity instance;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);

        instance = this;

        createDBs();

        btMap = findViewById(R.id.btMap);
        btMap.setOnClickListener(e -> startActivity(new Intent(getApplicationContext(), MapActivity.class)));

        daoForDay = dbForDay.RegionDAO();
        daoForPeriod = dbForPeriod.RegionDAO();

        if (daoForDay.getAll().size() == 0)
            createRegions();

        if (daoForPeriod.getAll().size() == 0)
            createRegionsInfoForPeriod();

        rv.setLayoutManager(new LinearLayoutManager(this));
        new SetListRegions(SetListRegions.LIST_FOR_DAY).execute();
        new SetListRegions(SetListRegions.LIST_FOR_PERIOD).execute();

    }

    public List<RegionInfo> getList() {
        return list;
    }


    private void createDBs() {
        //addMigration after updating DB (Class MigrationDB have the migration example)
        dbForDay = Room.databaseBuilder(this, RegionDatabase.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        dbForPeriod = Room.databaseBuilder(this, RegionDBForPeriod.class, "DBforPeriod").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
    }

    public RegionDBForPeriod getDbForPeriod() {
        return dbForPeriod;
    }

    public RegionDatabase getDbForDay() {
        return dbForDay;
    }

    private void createRegions() {
        XmlPullParser xpp = getResources().getXml(R.xml.regiones);
        RegionParser parser = new RegionParser();
        parser.parse(xpp);
        List<RegionInfo> list = parser.getListRegion();

        fillDB(QueryRegionInDB.DATABASE_FOR_DAY, list);
    }


    private void createRegionsInfoForPeriod() {
        XmlPullParser xpp = getResources().getXml(R.xml.regiones_info_for_period);
        RegionParser parser = new RegionParser();
        parser.parse(xpp);
        List<RegionInfo> list = parser.getListRegion();
        fillDB(QueryRegionInDB.DATABASE_FOR_PERIOD, list);
    }

    private void fillDB(int typeDB, List<RegionInfo> list) {
        if (list.size() > 0) {
            for (RegionInfo r : list) {
                new QueryRegionInDB(typeDB).execute(r);
            }
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public DAO getDaoForPeriod() {
        return daoForPeriod;
    }

    public List<RegionInfo> getListForPeriod() {
        return listForPeriod;
    }

    private class QueryRegionInDB extends AsyncTask<RegionInfo, Void, Void> {

        public static final int DATABASE_FOR_DAY = 0, DATABASE_FOR_PERIOD = 1;
        private int typeDB;

        QueryRegionInDB(int typeDB) {
            this.typeDB = typeDB;
        }

        @Override
        protected Void doInBackground(RegionInfo... regions) {
            RegionInfo region = regions[0];
            if (typeDB == DATABASE_FOR_DAY)
                daoForDay.insert(region);
            else if (typeDB == DATABASE_FOR_PERIOD)
                daoForPeriod.insert(region);
            return null;
        }
    }

    private class SetListRegions extends AsyncTask<Void, Void, Void> {

        public static final int LIST_FOR_DAY = 0, LIST_FOR_PERIOD = 1;
        private int typeList;

        SetListRegions(int typeList) {
            this.typeList = typeList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (typeList == LIST_FOR_DAY)
                list = daoForDay.getAll();
            else if (typeList == LIST_FOR_PERIOD)
                listForPeriod = daoForPeriod.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (typeList == LIST_FOR_DAY) {
                RegionAdapter regionAdapter = new RegionAdapter(list);
                rv.setAdapter(regionAdapter);
            }
        }
    }

}