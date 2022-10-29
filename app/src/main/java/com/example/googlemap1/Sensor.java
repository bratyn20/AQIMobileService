package com.example.googlemap1;

import java.util.Date;

public class Sensor {
    public double latitude;
    public double longitude;

    public String name;


    double aoi;

    public int id;
    public String name_sensor;
    public Date date_sensor;
    public int temp;
    public String wetness;
    public String press;
    public int aqi;

    Sensor(double latitude, double longitude, String name, int temp, double aoi){
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.temp = temp;
        this.aoi = aoi;
    }

    Sensor(){

    }
}
