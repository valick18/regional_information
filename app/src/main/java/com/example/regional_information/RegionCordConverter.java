package com.example.regional_information;

import androidx.room.TypeConverter;

import java.util.List;
import java.util.stream.Collectors;
public class RegionCordConverter {
    @TypeConverter
    public String fromObjectToString(RegionCord regionCord) {
        String data = regionCord.getId()+","+regionCord.getRegionName()+","+regionCord.getLat1()+","+regionCord.getLong2();
        return data;
    }
    @TypeConverter
    public RegionCord toObjectFromString(String data) {
        String[]strArr = data.split(",");
        String name = strArr[1];
        double latitude = Double.parseDouble(strArr[2]);
        double longitude = Double.parseDouble(strArr[3]);
        return new RegionCord(name,latitude,longitude);
    }
}

