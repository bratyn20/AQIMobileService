package com.example.googlemap1;

public class CalculateDistance {
    public static double distance(double myLat, double myLong, double statLat, double statLong){
        double earthRadius = 6372795;
        double lat1 = myLat * Math.PI / 180;
        double lat2 = statLat * Math.PI / 180;
        double long1 = myLong * Math.PI / 180;
        double long2 = statLong * Math.PI / 180;

        //Получаем угловое расстояние в радианах, и так как в радианах просто умножаем на радиус.
        double a = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1));
        return earthRadius * a;
    }

    public static double newlat(double lat, double dist, double az){
//        double lat_new = Math.sin(lat * Math.PI / 180) * Math.cos(dist * Math.PI / 180) + Math.cos(lat * Math.PI / 180) * Math.sin(dist * Math.PI / 180) *
//                Math.cos(az * Math.PI / 180);
//
//        lat_new = Math.asin(lat_new) * 180 / Math.PI;
        double lat_new = lat + (dist * Math.cos(Math.toRadians(az)));
        return lat_new;
    }

    public static double newlng(double lat, double lng, double dist, double az){
//        double lng_new = Math.sin(dist * Math.PI / 180)*Math.sin(az * Math.PI / 180)/(Math.cos(lat * Math.PI / 180)*Math.cos(dist * Math.PI / 180)-Math.sin(lat * Math.PI / 180)*Math.sin(dist * Math.PI / 180)*Math.cos(az * Math.PI / 180));
//        lng_new = lng + (Math.atan(lng_new));

        double lng_new = lng + (dist * Math.sin(Math.toRadians(az)));
        return lng_new;
    }

    public static String windDrow(double deg){

        if(deg == 0)
            return "Ю";
        if(deg < 45)
            return "ЮЮЗ";
        if(deg == 45)
            return "ЮЗ";
        if(deg< 90)
            return "ЗЮЗ";
        if(deg == 90)
            return "З";
        if(deg < 135)
            return "ЗСЗ";
        if(deg == 135)
            return "СЗ";
        if(deg < 180)
            return "CCЗ";
        if(deg == 180)
            return "C";
        if(deg < 225)
            return "ССВ";
        if(deg == 225)
            return "СВ";
        if(deg < 270)
            return "ВСВ";
        if(deg == 270)
            return "В";
        if(deg < 315)
            return "ВЮВ";
        if(deg == 315)
            return "ЮВ";
        if(deg < 360)
            return "ЮЮВ";
        if(deg == 360)
            return "Ю";

        return "нет данных";
    }
}
