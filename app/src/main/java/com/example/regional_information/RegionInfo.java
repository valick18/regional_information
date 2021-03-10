package com.example.regional_information;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "", indices = @Index(value = {"id"},unique = true))
public class RegionInfo {

    @PrimaryKey(autoGenerate = true)
   public long id;
   public String regionName;
   public int sick, hospitalized, dead, recovered;
   public int vaccinated, testedPCR, testedIFA;


    public RegionInfo(String regionName, int sick, int hospitalized, int dead, int recovered, int vaccinated, int testedPCR, int testedIFA){
        this.regionName = regionName;
        this.sick = sick;
        this.hospitalized = hospitalized;
        this.dead = dead;
        this.recovered = recovered;
        this.vaccinated = vaccinated;
        this.testedPCR = testedPCR;
        this.testedIFA = testedIFA;
    }

}
