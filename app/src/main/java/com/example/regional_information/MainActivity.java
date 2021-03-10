package com.example.regional_information;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.regional_information.database.DAO;
import com.example.regional_information.database.RegionDBForPeriod;
import com.example.regional_information.database.RegionDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RegionDatabase dbForDay;
    private DAO daoForDay;
    private DAO daoForPeriod;
    private RegionDBForPeriod dbForPeriod;

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

        daoForDay = dbForDay.RegionDAO();
        daoForPeriod = dbForPeriod.RegionDAO();

        if(daoForDay.getAll().size()==0)
            createRegions();

        if(daoForPeriod.getAll().size() == 0)
            createRegionsInfoForPeriod();

        rv.setLayoutManager(new LinearLayoutManager(this));
        new SetListRegions(SetListRegions.LIST_FOR_DAY).execute();
        new SetListRegions(SetListRegions.LIST_FOR_PERIOD).execute();
    }

    public List<RegionInfo> getList() {
        return list;
    }


    private void createDBs(){
        dbForDay = Room.databaseBuilder(this, RegionDatabase.class,"database").allowMainThreadQueries()
                .build();
        dbForPeriod = Room.databaseBuilder(this,RegionDBForPeriod.class,"DBforPeriod").allowMainThreadQueries()
                .build();
    }

    public RegionDBForPeriod getDbForPeriod() {
        return dbForPeriod;
    }

    public RegionDatabase getDbForDay() {
        return dbForDay;
    }

    private void createRegions(){
        RegionInfo regionBogorodchany = new RegionInfo("Богородчани",300,50,15,100,20,15,18);
        RegionInfo regionVerhovyna = new RegionInfo("Верховина",204,20,12,60,34,13,15);
        RegionInfo regionGalyich = new RegionInfo("Галич",100,31,12,56,31,18,9);
        RegionInfo regiondDolyna = new RegionInfo("Долина",153,16,10,57,28,19,5);
        RegionInfo regionIF = new RegionInfo("Івано-Франківськ",500,72,23,100,89,31,18);
        RegionInfo regionTismenyca = new RegionInfo("Тисмениця",100,10,3,23,20,15,18);
        fillDB(QueryRegionInDB.DATABASE_FOR_DAY,regionBogorodchany,regionVerhovyna,regionGalyich,regiondDolyna,regionIF,regionTismenyca);
    }


    private void createRegionsInfoForPeriod(){
        RegionInfo regionBogorodchany = new RegionInfo("Богородчани",35,300,3000,30000,300000,10000,10000);
        RegionInfo regionVerhovyna = new RegionInfo("Верховина",30,300,3000,30000,300000,10000,10000);
        RegionInfo regionGalyich = new RegionInfo("Галич",30,300,3000,30000,300000,10000,10000);
        RegionInfo regiondDolyna = new RegionInfo("Долина",30,300,3000,30000,300000,10000,10000);
        RegionInfo regionIF = new RegionInfo("Івано-Франківськ",50,300,3000,30000,300000,10000,10000);
        RegionInfo regionTismenyca = new RegionInfo("Тисмениця",30,300,3000,30000,300000,10000,10000);
        fillDB(QueryRegionInDB.DATABASE_FOR_PERIOD,regionBogorodchany,regionVerhovyna,regionGalyich,regiondDolyna,regionIF,regionTismenyca);
    }

    private void fillDB(int typeDB, RegionInfo ... regionsInfo){
        if(regionsInfo.length>0) {
            for (RegionInfo r : regionsInfo) {
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

    private class QueryRegionInDB extends AsyncTask<RegionInfo,Void,Void> {

        public static final int DATABASE_FOR_DAY = 0, DATABASE_FOR_PERIOD = 1;
        private int typeDB;

        QueryRegionInDB(int typeDB){
            this.typeDB = typeDB;
        }

        @Override
        protected Void doInBackground(RegionInfo ... regions) {
            RegionInfo region = regions[0];
            if(typeDB == DATABASE_FOR_DAY)
            daoForDay.insert(region);
            else if(typeDB == DATABASE_FOR_PERIOD)
                daoForPeriod.insert(region);
            return null;
        }
    }

    private class SetListRegions extends AsyncTask<Void, Void, Void>{

        public static final int LIST_FOR_DAY = 0, LIST_FOR_PERIOD = 1;
        private int typeList;

        SetListRegions(int typeList){
            this.typeList = typeList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(typeList == LIST_FOR_DAY)
              list = daoForDay.getAll();
            else if(typeList == LIST_FOR_PERIOD)
                listForPeriod = daoForPeriod.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(typeList == LIST_FOR_DAY) {
                RegionAdapter regionAdapter = new RegionAdapter(list);
                rv.setAdapter(regionAdapter);
            }
        }
    }

}