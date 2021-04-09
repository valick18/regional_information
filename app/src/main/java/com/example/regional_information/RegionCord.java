package com.example.regional_information;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"regionName","id"}, unique = true)})
public class RegionCord {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String regionName;
    private double lat1;
    private double long2;
    private Information information;

    public RegionCord(String regionName, double lat1, double long2) {
            this.regionName = regionName;
            this.lat1 = lat1;
            this.long2 = long2;
    }

        public RegionCord() {
        }

        public Information getInformation() {
            return information;
        }

        public void setInformation(Information information) {
            this.information = information;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public double getLat1() {
            return lat1;
        }

        public void setLat1(double lat1) {
            this.lat1 = lat1;
        }

        public double getLong2() {
            return long2;
        }

        public void setLong2(double long2) {
            this.long2 = long2;
        }

        @Override
        public String toString() {
            return "RegionCord{" +
                    "id=" + id +
                    ", regionName='" + regionName + '\'' +
                    ", lat1=" + lat1 +
                    ", long2=" + long2 +
                    '}';
        }
    }