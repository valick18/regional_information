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
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
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


    private void drawCircle(int radius, LatLng point, int color) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(point);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.BLACK);
        circleOptions.fillColor(color);
        circleOptions.strokeWidth(2);
        gMap.addCircle(circleOptions);
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
        drawPolygon(new LatLng(48.95375939873926, 24.689202138885474),
                new LatLng(48.94857380159112, 24.678644964598885),
                new LatLng(48.946544508166006, 24.678559133913627),
                new LatLng(48.946262655329846, 24.67958910213671),
                new LatLng(48.94406414857073, 24.681048223786078),
                new LatLng(48.94169641758721, 24.67950327145145),
                new LatLng(48.937468047144776, 24.686970541068796),
                new LatLng(48.9360021284438, 24.68739969449508),
                new LatLng(48.933521244878584, 24.69229204355472),
                new LatLng(48.92500636596547, 24.679760763507222),
                new LatLng(48.922181263235466, 24.682381709408425),
                new LatLng(48.917490264330084, 24.67583943940999),
                new LatLng(48.915824085120924, 24.67578047571683),
                new LatLng(48.90636843438563, 24.681028244408097),
                new LatLng(48.904510387663315, 24.679306678296488),
                new LatLng(48.902184867053876, 24.680721806932333),
                new LatLng(48.900634459862914, 24.679542533069128),
                new LatLng(48.899704192462806, 24.680014242614412),
                new LatLng(48.89598294971945, 24.678952896137524),
                new LatLng(48.895163571622895, 24.691335673553063),
                new LatLng(48.88217617401557, 24.68984387647211),
                new LatLng(48.8799627674204, 24.704709325679914),
                new LatLng(48.87714975519967, 24.705340406070814),
                new LatLng(48.87604295292798, 24.702185004116327),
                new LatLng(48.872860759870825, 24.700992963425307),
                new LatLng(48.87175386270444, 24.70393800524949),
                new LatLng(48.87258403787509, 24.705691006335318),
                new LatLng(48.869862856492716, 24.7058312465353),
                new LatLng(48.871846104837275, 24.71536757244219),
                new LatLng(48.874013751478486, 24.717330933658317),
                new LatLng(48.87484388915022, 24.71669985326742),
                new LatLng(48.88194561517945, 24.722800297282834),
                new LatLng(48.884297263270426, 24.721257656350982),
                new LatLng(48.8910288207341, 24.7240624580883),
                new LatLng(48.89513186004539, 24.731986023077784),
                new LatLng(48.89729849762873, 24.736263345727192),
                new LatLng(48.897575082901376, 24.738086466856455),
                new LatLng(48.902000239223675, 24.740190068283084),
                new LatLng(48.91564200502086, 24.744958231489083),
                new LatLng(48.92904970495813, 24.75877188028478),
                new LatLng(48.931905863348724, 24.75175987614401),
                new LatLng(48.93642010273917, 24.755265878315665),
                new LatLng(48.945401303797055, 24.735702386089994),
                new LatLng(48.94231563752407, 24.731705543614304),
                new LatLng(48.94586183472689, 24.7243429385409),
                new LatLng(48.94424995818585, 24.71817237471879),
                new LatLng(48.942407749815544, 24.715998653372367),
                new LatLng(48.94973012600613, 24.69713636168888),
                new LatLng(48.949315680784345, 24.696505279827594),
                new LatLng(48.953690208996534, 24.689002435180257)
        );
    }

    private void drawAllElements() {
        List<RegionInfo> list = MainActivity.getInstance().getList();
        for (int i = 0; i < list.size(); i++) {
            RegionInfo regionInfo = list.get(i);

            String sick = "" + regionInfo.sick;

            double lat1 = regionInfo.lat1;
            double long2 = regionInfo.long2;

            drawCircle(2000 * sick.length(), new LatLng(lat1, long2), 0x30ff0000);
        }

    }

}
