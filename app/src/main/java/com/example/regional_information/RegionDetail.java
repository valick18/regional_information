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

    public void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("index");
        region = MainActivity.getInstance().getList().get(position);
        ArrayList<RegionInfoMap> list = new ArrayList<>();
        list.add(new RegionInfoMap("",region.regionName));
        list.add(new RegionInfoMap("", "За добу"));
        list.add(new RegionInfoMap("Захворіло",""+region.sick));
        list.add(new RegionInfoMap("Шпиталізовано",""+region.hospitalized));
        list.add(new RegionInfoMap("Померло",""+region.dead));
        list.add(new RegionInfoMap("Одужало",""+region.recovered));
        list.add(new RegionInfoMap("Вакциновано",""+region.vaccinated));
        list.add(new RegionInfoMap("Тестовано ПЛР",""+region.testedPCR));
        list.add(new RegionInfoMap("Тестовано ІФА",""+region.testedIFA));
        String from[] = {RegionInfoMap.key1,RegionInfoMap.key2};
        int to[] = {R.id.tvInfo,R.id.tvNum};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.region_detail,from,to);
        setListAdapter(simpleAdapter);
    }

        private class RegionInfoMap extends HashMap<String,String> {
              static final String key1 = "info", key2 = "number";

             RegionInfoMap(String info, String number){
                this.put(key1,info);
                this.put(key2,number);
             }
        }


}
