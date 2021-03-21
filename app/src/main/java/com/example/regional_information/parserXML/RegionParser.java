package com.example.regional_information.parserXML;

import com.example.regional_information.R;
import com.example.regional_information.RegionInfo;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class RegionParser {
    private ArrayList<RegionInfo> listRegion;

    public RegionParser() {
        listRegion = new ArrayList<>();
    }

    public ArrayList<RegionInfo> getListRegion() {
        return listRegion;
    }

    public boolean parse(XmlPullParser xpp) {
        boolean status = true;
        RegionInfo regionInfo = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("region".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            regionInfo = new RegionInfo();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("region".equalsIgnoreCase(tagName)) {
                                listRegion.add(regionInfo);
                                inEntry = false;
                            } else if ("lat".equalsIgnoreCase(tagName)) {
                                regionInfo.setLat1(Double.parseDouble(textValue));
                            } else if ("long".equalsIgnoreCase(tagName)) {
                                regionInfo.setLong2(Double.parseDouble(textValue));
                            } else if ("name-region".equalsIgnoreCase(tagName)) {
                                regionInfo.setRegionName(textValue);
                            } else if ("sick".equalsIgnoreCase(tagName)) {
                                regionInfo.setSick(Integer.parseInt(textValue));
                            } else if ("hospitalized".equalsIgnoreCase(tagName)) {
                                regionInfo.setHospitalized(Integer.parseInt(textValue));
                            } else if ("dead".equalsIgnoreCase(tagName)) {
                                regionInfo.setDead(Integer.parseInt(textValue));
                            } else if ("recoverd".equalsIgnoreCase(tagName)) {
                                regionInfo.setRecovered(Integer.parseInt(textValue));
                            } else if ("vaccinated".equalsIgnoreCase(tagName)) {
                                regionInfo.setVaccinated(Integer.parseInt(textValue));
                            } else if ("tested-pcr".equalsIgnoreCase(tagName)) {
                                regionInfo.setTestedPCR(Integer.parseInt(textValue));
                            } else if ("tested-ifa".equalsIgnoreCase(tagName)) {
                                regionInfo.setTestedIFA(Integer.parseInt(textValue));
                            } else if ("color-zone".equalsIgnoreCase(tagName)) {
                                int color = 0;
                                switch (textValue) {
                                    case "green":
                                        color = R.color.green;
                                        break;
                                    case "red":
                                        color = R.color.red;
                                        break;
                                    case "yellow":
                                        color = R.color.yellow;
                                        break;
                                }
                                regionInfo.setColorZone(color);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
