package com.example.regional_information;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity(indices = {@Index(value = {"id"}, unique = true)})
public class Information {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(RegionCordConverter.class)
    private RegionCord regionCord;
    private int sick;
    private int hospitalized;
    private int dead;
    private int recovered;
    private int vaccinated;
    private int testedPCR;
    private int testedIFA;

    public Information(RegionCord regionCord, int sick, int hospitalized, int dead, int recovered, int vaccinated, int testedPCR, int testedIFA) {
        this.regionCord = regionCord;
        this.sick = sick;
        this.hospitalized = hospitalized;
        this.dead = dead;
        this.recovered = recovered;
        this.vaccinated = vaccinated;
        this.testedPCR = testedPCR;
        this.testedIFA = testedIFA;
    }

    public Information() {
    }

    public RegionCord getRegionCord() {
        return regionCord;
    }

    public void setRegionCord(RegionCord regionCord) {
        this.regionCord = regionCord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", regionCord=" + regionCord +
                ", sick=" + sick +
                ", hospitalized=" + hospitalized +
                ", dead=" + dead +
                ", recovered=" + recovered +
                ", vaccinated=" + vaccinated +
                ", testedPCR=" + testedPCR +
                ", testedIFA=" + testedIFA +
                '}';

    }
}
