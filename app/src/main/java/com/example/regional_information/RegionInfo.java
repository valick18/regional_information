package com.example.regional_information;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"regionName", "id"}, unique = true)})
public class RegionInfo {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String regionName;
    private int sick;

    private int hospitalized;
    private int dead;
    private int recovered;
    private int vaccinated, testedPCR, testedIFA;
    private double lat1, long2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getSick() {
        return sick;
    }

    public void setSick(int sick) {
        this.sick = sick;
    }

    public int getHospitalized() {
        return hospitalized;
    }

    public void setHospitalized(int hospitalized) {
        this.hospitalized = hospitalized;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(int vaccinated) {
        this.vaccinated = vaccinated;
    }

    public int getTestedPCR() {
        return testedPCR;
    }

    public void setTestedPCR(int testedPCR) {
        this.testedPCR = testedPCR;
    }

    public int getTestedIFA() {
        return testedIFA;
    }

    public void setTestedIFA(int testedIFA) {
        this.testedIFA = testedIFA;
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
        return "RegionInfo{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                ", sick=" + sick +
                ", hospitalized=" + hospitalized +
                ", dead=" + dead +
                ", recovered=" + recovered +
                ", vaccinated=" + vaccinated +
                ", testedPCR=" + testedPCR +
                ", testedIFA=" + testedIFA +
                ", lat1=" + lat1 +
                ", long2=" + long2 +
                '}';
    }
}
