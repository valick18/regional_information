package com.example.regional_information;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.regional_information.database.DAO;

import java.util.List;

public class RegionDetail extends Activity {
    private TextView tvSick,tvRegion,tvDead, tvNowBetter;
    private TextView tvVaccinated,tvPLR,tvIPA, tvHospit;
    private RegionInfo region;

    public void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);
        setContentView(R.layout.region_detail);
        init();
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("index");
        region = MainActivity.getInstance().getList().get(position);
        fillTextView();
    }

    private void init(){
        tvSick = findViewById(R.id.tvSicks);
        tvRegion = findViewById(R.id.regionName);
        tvDead = findViewById(R.id.tvDead);
        tvVaccinated = findViewById(R.id.tvVaccinated);
        tvPLR = findViewById(R.id.tvPLR);
        tvIPA = findViewById(R.id.tvIPA);
        tvHospit = findViewById(R.id.tvHospit);
        tvNowBetter = findViewById(R.id.tvRecovered);
    }

    private void fillTextView(){
        tvRegion.setText(region.regionName);
        tvSick.setText(""+region.sick);
        tvDead.setText(""+region.dead);
        tvVaccinated.setText(""+region.vaccinated);
        tvPLR.setText(""+region.testedPCR);
        tvIPA.setText(""+region.testedIFA);
        tvHospit.setText(""+region.hospitalized);
        tvNowBetter.setText(""+region.recovered);
    }




}
