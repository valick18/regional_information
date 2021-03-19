package com.example.regional_information;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.map_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void drawCircle(int radius, LatLng point, int color) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(point);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.BLACK);
        circleOptions.fillColor(color);
        circleOptions.strokeWidth(2);
        gMap.addCircle(circleOptions);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        drawAllElements();
    }

    private void drawAllElements(){
    List<RegionInfo> list = MainActivity.getInstance().getList();
    for(int i = 0; i < list.size(); i++){
        RegionInfo regionInfo = list.get(i);

        String sick = ""+regionInfo.sick;

        double lat1 = regionInfo.lat1;
        double long2 = regionInfo.long2;

        drawCircle(2000*sick.length(), new LatLng(lat1,long2),0x30ff0000);
    }

    }

}
