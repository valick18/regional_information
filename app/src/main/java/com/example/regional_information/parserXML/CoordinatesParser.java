package com.example.regional_information.parserXML;

import com.example.regional_information.parserXML.Coordinates;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class CoordinatesParser {

        private ArrayList<Coordinates> coordinatesList;

        public CoordinatesParser(){
            coordinatesList = new ArrayList<>();
        }

        public ArrayList<Coordinates> getCoordinatesList(){
            return  coordinatesList;
        }

        public boolean parse(XmlPullParser xpp){
            boolean status = true;
            Coordinates currentCoordinates = null;
            boolean inEntry = false;
            String textValue = "";

            try{
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){

                    String tagName = xpp.getName();
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if("coordinates".equalsIgnoreCase(tagName)){
                                inEntry = true;
                                currentCoordinates = new Coordinates();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            textValue = xpp.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(inEntry){
                                if("coordinates".equalsIgnoreCase(tagName)){
                                    coordinatesList.add(currentCoordinates);
                                    inEntry = false;
                                } else if("lat".equalsIgnoreCase(tagName)){
                                    double value = Double.parseDouble(textValue.replace(",","."));
                                    currentCoordinates.setLatitude(value);
                                } else if("long".equalsIgnoreCase(tagName)){
                                    double value = Double.parseDouble(textValue.replace(",","."));
                                    currentCoordinates.setLongitude(value);
                                }
                            }
                            break;
                        default:
                    }
                    eventType = xpp.next();
                }
            }
            catch (Exception e){
                status = false;
                e.printStackTrace();
            }
            return  status;
        }
    }