package com.example.regional_information;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.regional_information.parserXML.Coordinates;
import com.example.regional_information.parserXML.CoordinatesParser;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void drawCircle(RegionInfo region, LatLng point) {
        int radius = findRadius(region.getSick());
        List<RegionInfo> list = MainActivity.getInstance().getList();
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(point);
        circleOptions.radius(radius);

        circleOptions.strokeColor(Color.BLACK);
        circleOptions.fillColor(Color.BLUE);
        circleOptions.strokeWidth(2);

        CircleOptions innerCircle = new CircleOptions();
        innerCircle.center(point);
        innerCircle.radius(3000);
        innerCircle.fillColor(getInnerColorZone(region));
        innerCircle.strokeWidth(2);

        gMap.addCircle(circleOptions);
        gMap.addCircle(innerCircle);

        gMap.addMarker(new MarkerOptions()
                .alpha(0)
                .position(point)
                .title(""+region.getRegionName())
                .snippet("Померло за добу: " + region.getDead()+
                        "\n" + "Виздоровіло: "+ region.getRecovered()+"\nЗахворіло: "+region.getSick()));

        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapActivity.this);
        gMap.setInfoWindowAdapter(adapter);
    }

    private int getInnerColorZone(RegionInfo region) {
        int vaccinated = region.getVaccinated();
        int color = 0;
        if(vaccinated >= 100){
         color = Color.GREEN;
        }else if(vaccinated > 69 && vaccinated<100){
            color = Color.YELLOW;
        }else if(vaccinated > 49 && vaccinated < 70 ){
            color = Color.rgb(255,180,68);
        }else{
            color = Color.rgb(255,76,91);
        }
        return color;
    }

    private void drawPolygon(LatLng... point) {
        PolygonOptions polygonOptions = new PolygonOptions()
                .strokeColor(Color.RED)
                .strokeWidth(3)
                .fillColor(0xE8EAED);
        polygonOptions.add(point);

        gMap.addPolygon(polygonOptions);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        drawAllElements();
        drawPolygonForFrankivsk();
    }


    private void drawPolygonForFrankivsk() {

        XmlPullParser xpp = getResources().getXml(R.xml.iv_frank_coordinates);
        CoordinatesParser parser = new CoordinatesParser();

        parser.parse(xpp);
        List<Coordinates> coordinatesList = parser.getCoordinatesList();
        int size = coordinatesList.size();
        LatLng[] latLngs = new LatLng[size];
        for (int i = 0; i < size; i++) {
            Coordinates cs = coordinatesList.get(i);
            latLngs[i] = new LatLng(cs.getLatitude(), cs.getLongitude());
        }

        drawPolygon(latLngs);
    }

    private void drawAllElements() {
        List<RegionInfo> list = MainActivity.getInstance().getList();
        for (int i = 0; i < list.size(); i++) {
            RegionInfo regionInfo = list.get(i);

            double lat1 = regionInfo.getLat1();
            double long2 = regionInfo.getLong2();

            drawCircle(regionInfo, new LatLng(lat1, long2));
        }

    }

    private int findRadius(int sick){
      int rad = sick;
        if(rad < 50){
            rad = 50;
        }else{
            if (rad > 1200){
                rad = 1200;
            }
        }
        return (rad + 800) * 6;
    }


}
