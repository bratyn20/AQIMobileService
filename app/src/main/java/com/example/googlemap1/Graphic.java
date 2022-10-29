package com.example.googlemap1;

import java.util.Date;

public class Graphic implements Comparable<Graphic>{

    Date x;
    public Double y;

    public Graphic(Date x,Double y) {
        this.x = x;
        this.y = y;
    }

    public Date getDateTime(Date x){
        return x;
    }


    @Override
    public int compareTo(Graphic graphic) {
        return x.compareTo(graphic.x);
    }
}
