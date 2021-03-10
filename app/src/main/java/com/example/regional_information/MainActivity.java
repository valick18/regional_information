package com.example.regional_information;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.regional_information.database.DAO;
import com.example.regional_information.database.RegionDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RegionDatabase database;
    private DAO dao;

    private List<RegionInfo> list;
    private static MainActivity instance;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rv = findViewById(R.id.rv);

        instance = this;

        createDB();

        dao = database.RegionDAO();
        if(dao.getAll().size()==0)
            createRegions();

        rv.setLayoutManager(new LinearLayoutManager(this));
        new SetListRegions().execute();

    }

    public List<RegionInfo> getList() {
        return list;
    }

    private void createDB(){
        database = Room.databaseBuilder(this, RegionDatabase.class,"database").allowMainThreadQueries()
                .build();
    }

    public RegionDatabase getDatabase() {
        return database;
    }

    private void createRegions(){
        RegionInfo regionBogorodchany = new RegionInfo("Богородчани",300,50,15,100,20,15,18);
        RegionInfo regionVerhovyna = new RegionInfo("Верховина",204,20,12,60,34,13,15);
        RegionInfo regionGalyich = new RegionInfo("Галич",100,31,12,56,31,18,9);
        RegionInfo regiondDolyna = new RegionInfo("Долина",153,16,10,57,28,19,5);
        RegionInfo regionIF = new RegionInfo("Івано-Франківськ",500,72,23,100,89,31,18);
        RegionInfo regionTismenyca = new RegionInfo("Тисмениця",100,10,3,23,20,15,18);
        fillDB(regionBogorodchany,regionVerhovyna,regionGalyich,regiondDolyna,regionIF,regionTismenyca);
    }

    private void fillDB(RegionInfo ... regionsInfo){
        if(regionsInfo.length>0) {
            for (RegionInfo r : regionsInfo) {
                new QueryRegionInDB().execute(r);
            }
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }


    private class QueryRegionInDB extends AsyncTask<RegionInfo,Void,Void> {

        @Override
        protected Void doInBackground(RegionInfo ... regions) {
            RegionInfo region = regions[0];
            dao.insert(region);
            return null;
        }
    }

    private class SetListRegions extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            list = dao.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            RegionAdapter regionAdapter = new RegionAdapter(list);
            rv.setAdapter(regionAdapter);
        }
    }

}