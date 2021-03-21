package com.example.regional_information;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.regional_information.database.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegionDetail extends ListActivity {

    private RegionInfo region;
    private RegionInfo regionInfoFP;
    private ArrayList<RegionInfoMap> list;

    public void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("index");

        region = MainActivity.getInstance().getList().get(position);
        regionInfoFP = MainActivity.getInstance().getListForPeriod().get(position);

        initList();

        String from[] = {RegionInfoMap.key1,RegionInfoMap.key2, RegionInfoMap.key3};
        int to[] = {R.id.tvInfo,R.id.tvNum, R.id.tvPeriodInfo};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.region_detail,from,to);
        setListAdapter(simpleAdapter);
    }

    private void initList(){
        list = new ArrayList<>();
        list.add(new RegionInfoMap("",region.getRegionName(), ""));
        list.add(new RegionInfoMap("", "За добу", "За період"));
        list.add(new RegionInfoMap("Захворіло",""+region.getSick(), ""+regionInfoFP.getSick()));
        list.add(new RegionInfoMap("Шпиталізовано",""+region.getHospitalized(), ""+regionInfoFP.getHospitalized()));
        list.add(new RegionInfoMap("Померло",""+region.getDead(), ""+regionInfoFP.getDead()));
        list.add(new RegionInfoMap("Одужало",""+region.getRecovered(), ""+regionInfoFP.getRecovered()));
        list.add(new RegionInfoMap("Вакциновано",""+region.getVaccinated(), ""+regionInfoFP.getVaccinated()));
        list.add(new RegionInfoMap("Тестовано ПЛР",""+region.getTestedPCR(), ""+regionInfoFP.getTestedPCR()));
        list.add(new RegionInfoMap("Тестовано ІФА",""+region.getTestedIFA(), ""+regionInfoFP.getTestedIFA()));
    }

        private class RegionInfoMap extends HashMap<String,String> {
            static final String key1 = "info", key2 = "number", key3 = "periodInfo";

             RegionInfoMap(String info, String number, String infoPeriod){
                this.put(key1,info);
                this.put(key2,number);
                this.put(key3,infoPeriod);
             }
        }


}
