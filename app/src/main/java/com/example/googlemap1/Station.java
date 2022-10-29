package com.example.googlemap1;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

public class Station {
    public int stationID;
    public double latitude;
    public double longitude;
    public String name_station;
    public String majorName;

    public ArrayList<Measurment> measurments = new ArrayList<Measurment>();

    public Marker marker;

    public double deg;
    public double windSpeed;
    public String nameAPI;

    public double distance;

}
