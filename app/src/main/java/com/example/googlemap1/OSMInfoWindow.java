package com.example.googlemap1;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.ArrayList;
import java.util.List;

public class OSMInfoWindow extends InfoWindow {

    Station station;
    LinearLayout mminfo;
    //LinearLayoutViews
    Polygon polygon1;


    public OSMInfoWindow(int layoutResId, MapView mapView, Station station, Polygon polygon1, FragmentManager fragmentManager) {
        super(layoutResId, mapView);
        this.station = station;
        this.polygon1 = polygon1;
        mminfo = mView.findViewById(R.id.mminfo);

        LinearLayout.LayoutParams layoutParamsName = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParamsName.gravity = Gravity.CENTER_HORIZONTAL;

        TextView textViewName = new TextView(mView.getContext());
        if(station.name_station == null) {
            textViewName.setText(Html.fromHtml("<font color='#6200EE'><b>Адрес: </b></font>"
                    + station.majorName));
        }
        else {
            textViewName.setText(Html.fromHtml("<font color='#6200EE'><b>Адрес: </b></font>"
                    + station.majorName + ", " + station.name_station));
        }
        textViewName.setTextColor(0xFF000000);
        textViewName.setLayoutParams(layoutParamsName);
        mminfo.addView(textViewName);

        TextView textViewName2 = new TextView(mView.getContext());
        textViewName2.setText(Html.fromHtml("<font color='#6200EE'><b>Расстояние до станции: </b></font>"
                + String.format("%.0f",station.distance) + " м."));
        textViewName2.setTextColor(0xFF000000);
        textViewName2.setLayoutParams(layoutParamsName);
        mminfo.addView(textViewName2);


        LinearLayout linearLayoutStation = new LinearLayout(mView.getContext());
        LinearLayout.LayoutParams layoutParamsStation = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutStation.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutStation.setLayoutParams(layoutParamsStation);

        LinearLayout.LayoutParams layoutParamsLat = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParamsLat.weight = 1;

        LinearLayout.LayoutParams layoutParamsLong = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParamsLong.weight = 1;

        TextView textViewLat = new TextView(mView.getContext());
        textViewLat.setText(Html.fromHtml("<font color='#6200EE'><b>Широта: </b></font>"
                + String.format("%.2f", station.latitude)));
        textViewLat.setTextColor(0xFF000000);
        textViewLat.setLayoutParams(layoutParamsLat);
        linearLayoutStation.addView(textViewLat);

        TextView textViewLong = new TextView(mView.getContext());
        textViewLong.setText(Html.fromHtml("<font color='#6200EE'><b>Долгота: </b></font>"
                + String.format("%.2f", station.longitude)));
        textViewLong.setTextColor(0xFF000000);
        textViewLong.setLayoutParams(layoutParamsLong);
        linearLayoutStation.addView(textViewLong);

        mminfo.addView(linearLayoutStation);

        for(Measurment measurment : station.measurments){

            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams1.weight = 1;

            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams2.weight = 5;

            LinearLayout.LayoutParams layoutParamsDisc = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParamsDisc.gravity = Gravity.CENTER_HORIZONTAL;

            TextView textViewDisc = new TextView(mView.getContext());
            textViewDisc.setText(measurment.description);
            textViewDisc.setTextColor(0xFF000000);
            textViewDisc.setLayoutParams(layoutParamsDisc);
            mminfo.addView(textViewDisc);


            LinearLayout linearLayoutCurr = new LinearLayout(mView.getContext());
            LinearLayout.LayoutParams layoutParamsCurr = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayoutCurr.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutCurr.setLayoutParams(layoutParamsCurr);



            TextView textView = new TextView(mView.getContext());
            textView.setText(measurment.value + " " + measurment.nameparameter);

            textView.setTextColor(0xFF9100A1);

            textView.setLayoutParams(layoutParams1);
            linearLayoutCurr.addView(textView);

            TextView textView2 = new TextView(mView.getContext());

            if(measurment.recomendation == null){
                textView2.setText("Нет рекомендаций");
                textView2.setTextColor(0xFF007a05);
            }
            else
            {
                textView2.setText("Есть рекомендации");
                textView2.setTextColor(0xFFcc0002);
            }

            textView2.setLayoutParams(layoutParams2);
            linearLayoutCurr.addView(textView2);

            mminfo.addView(linearLayoutCurr);
        }

        mminfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OSMCustomDialogFragment dialog = new OSMCustomDialogFragment(station);
                dialog.show(fragmentManager, "custom");
            }
        });

//        List<GeoPoint> geoPoints1 = new ArrayList<>();
//        geoPoints1.add(new GeoPoint(CalculateDistance.newlat(station.latitude, station.windSpeed * 0.001 * 60,station.deg - 180 ), CalculateDistance.newlng(station.latitude,station.longitude,station.windSpeed * 0.001 * 60 ,station.deg - 180)));
//        geoPoints1.add(new GeoPoint(CalculateDistance.newlat(station.latitude, station.windSpeed * 0.001 / 2 * 30,station.deg -90), CalculateDistance.newlng(station.latitude,station.longitude,station.windSpeed * 0.001 /2 * 30,station.deg - 90)));
//        geoPoints1.add(new GeoPoint(CalculateDistance.newlat(station.latitude, station.windSpeed * 0.001 / 2 * 60,station.deg - 180), CalculateDistance.newlng(station.latitude,station.longitude,station.windSpeed * 0.001 / 2 * 60 ,station.deg - 180)));
//        geoPoints1.add(new GeoPoint(CalculateDistance.newlat(station.latitude, station.windSpeed * 0.001 / 2 * 30,station.deg + 90), CalculateDistance.newlng(station.latitude,station.longitude,station.windSpeed * 0.001 / 2 * 30,station.deg + 90)));
//        polygon1 = new Polygon();    //see note below
//        polygon1.setFillColor(Color.argb(75, 255,0,0));
//        geoPoints1.add(geoPoints1.get(0));    //forces the loop to close
//        polygon1.setPoints(geoPoints1);
        // polygon1.setTitle("A sample polygon");

        //this.station = station;
    }

    @Override
    public void onOpen(Object item) {


      //  map.getOverlays().add(polygon1);
        getMapView().getOverlays().add(polygon1);

//        TextView textView = (TextView) mView.findViewById(R.id.textView);
//        textView.setText("" + ((Marker)item).getTitle() + text1);
    }

    @Override
    public void onClose() {

           // getMapView().getOverlays().remove(polygon1);
    }
}
