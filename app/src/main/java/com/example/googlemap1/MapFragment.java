package com.example.googlemap1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment
        implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback
{

    SupportMapFragment supportMapFragment;

  //  ArrayList<Sensor> sensors = new ArrayList<Sensor>();

    public Map<Integer, Station> stationMap = new HashMap<Integer, Station>();

    private final static String TAG = "MainActivity";
    Marker lastMarker;

    TextView textDistance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        sensors.add(new Sensor(56.511342, 85.017313, "sensor1", -10, 3.71));
//        sensors.add(new Sensor(56.481346, 84.984187, "sensor2", -5, 3.30));
//        sensors.add(new Sensor(56.456286, 84.950380, "sensor3", -10, 4.00));


        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Initialize map fragment
        supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        this.stationMap = ((MainActivity) getActivity()).stationMap;

        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //When map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        //When clicker
                        //Initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        //Set positions of marker
                        markerOptions.position(latLng);
                        //Set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        //Set color of marker
                        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

                        //Set custom marker!!!
                        markerOptions.icon(bitmapDescriptorForVector(inflater.getContext(), R.drawable.ic_baseline_flag_24));

                        //Remove All marker
                        googleMap.clear();
                        //Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));

                        //Add marker on map
                        googleMap.addMarker(markerOptions);

                    }
                });

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        Toast toast = Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT);
                        toast.show();

                        return false;
                    }
                });
            }
        });

        //Return View
        return view;
    }

    private BitmapDescriptor bitmapDescriptorForVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       // Button updateButton = view.findViewById(R.id.btnTest);

        supportMapFragment.getMapAsync(this);

        ImageButton infobtn = view.findViewById(R.id.infobtn);
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Info TOAST", Toast.LENGTH_LONG).show();

                CustomDialogInfoFragment dialogInfoFragment = new CustomDialogInfoFragment();
                dialogInfoFragment.show(getChildFragmentManager(), "InfoCustomFragment");
            }
        });

        textDistance = view.findViewById(R.id.text_distance);
        double stationclose = CalculateDistance.distance(((MainActivity) getActivity()).myLat, ((MainActivity) getActivity()).myLong, ((MainActivity) getActivity()).stationclose.latitude, ((MainActivity) getActivity()).stationclose.longitude);
        textDistance.setText("Расстояние до ближайшей станции " + String.format("%.0f",stationclose) + " м.");

        //updateButton.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
//        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(@NonNull GoogleMap googleMap) {
//                //When map is loaded
////                        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Hello world"));
////                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
////                                new LatLng(0,0),10)
////                        );
//
//                googleMap.clear();
//
//                        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
//                            Log.d(TAG,"Карта " + String.valueOf(stationEntry.getValue().stationID));
//                            googleMap.addMarker(new MarkerOptions()
//                                                    .position(new LatLng(stationEntry.getValue().latitude, stationEntry.getValue().longitude))
//                                                    .title("test")
//                                                    .snippet("Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n")
//                                                    .icon(bitmapDescriptorForVector(view.getContext(),R.drawable.ic_baseline_flag_24)));
//                        }
////                        for (int i = 0 ;i < sensors.size(); i++){
////                            googleMap.addMarker(new MarkerOptions()
////                                                    .position(new LatLng(sensors.get(i).latitude,sensors.get(i).longitude))
////                                                    .title(sensors.get(i).name)
////                                                    .snippet("Температура: " + Integer.toString(sensors.get(i).temp) + ", " +
////                                                            "Уровень загрязненности (AOI): " + Double.toString(sensors.get(i).aoi)));
////
////                        }
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                                new LatLng(56.488627, 84.995925),12));
//                    }
//                });
        //  }
        //  });

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.clear();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);

        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
            Log.d(TAG,"Карта " + String.valueOf(stationEntry.getValue().stationID));

            boolean warning = false;
            for (Measurment stat: stationEntry.getValue().measurments){
                if(stat.recomendation != null)
                {
                    warning = true;
                }
            }


            if(warning != true) {
                stationEntry.getValue().marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(stationEntry.getValue().latitude, stationEntry.getValue().longitude))
                        .title("test")
                        .snippet("Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n")
                        .icon(bitmapDescriptorForVector(this.getContext(), R.drawable.ic_ok)));
            }
            else {
                stationEntry.getValue().marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(stationEntry.getValue().latitude, stationEntry.getValue().longitude))
                        .title("test")
                        .snippet("Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n" + "Test" + "\n")
                        .icon(bitmapDescriptorForVector(this.getContext(), R.drawable.ic_warning)));
            }
        }
//                        for (int i = 0 ;i < sensors.size(); i++){
//                            googleMap.addMarker(new MarkerOptions()
//                                                    .position(new LatLng(sensors.get(i).latitude,sensors.get(i).longitude))
//                                                    .title(sensors.get(i).name)
//                                                    .snippet("Температура: " + Integer.toString(sensors.get(i).temp) + ", " +
//                                                            "Уровень загрязненности (AOI): " + Double.toString(sensors.get(i).aoi)));
//
//                        }

        //линия расстояния
        PolylineOptions polylineOptions = new PolylineOptions().add(new LatLng(((MainActivity)getActivity()).myLat, ((MainActivity)getActivity()).myLong))
                .add(new LatLng(((MainActivity)getActivity()).stationclose.latitude, ((MainActivity)getActivity()).stationclose.longitude)).color(Color.RED).width(5);
        googleMap.addPolyline(polylineOptions);

        //точка
        CircleOptions circleOptions1 = new CircleOptions().center(new LatLng(((MainActivity)getActivity()).myLat, ((MainActivity)getActivity()).myLong)).radius(10)
                .fillColor(Color.RED);
        googleMap.addCircle(circleOptions1);

        //точка
        CircleOptions circleOptions2 = new CircleOptions().center(new LatLng(((MainActivity)getActivity()).stationclose.latitude, ((MainActivity)getActivity()).stationclose.longitude))
                .radius(10)
                .fillColor(Color.RED);
        googleMap.addCircle(circleOptions2);


        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
            PolygonOptions polygone3 = new PolygonOptions()
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 * 60 * 60,stationEntry.getValue().deg - 180 ), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 * 60 * 60 ,stationEntry.getValue().deg - 180)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 / 2 * 30 * 60,stationEntry.getValue().deg -90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 /2 * 30 * 60,stationEntry.getValue().deg - 90)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 / 2 * 60 * 60,stationEntry.getValue().deg - 180), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 / 2 * 60 * 60 ,stationEntry.getValue().deg - 180)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 / 2 * 30 * 60,stationEntry.getValue().deg + 90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 / 2 * 30 * 60,stationEntry.getValue().deg + 90)))
                    .strokeColor(Color.BLACK).strokeWidth(2).fillColor(0x14C70000);

            Polygon polygon3 = googleMap.addPolygon(polygone3);

            PolygonOptions polygone = new PolygonOptions()
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 * 60,stationEntry.getValue().deg - 180 ), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 * 60 ,stationEntry.getValue().deg - 180)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 / 2 * 30,stationEntry.getValue().deg -90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 /2 * 30,stationEntry.getValue().deg - 90)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 / 2 * 60,stationEntry.getValue().deg - 180), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 / 2 * 60 ,stationEntry.getValue().deg - 180)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.00001 / 2 * 30,stationEntry.getValue().deg + 90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.00001 / 2 * 30,stationEntry.getValue().deg + 90)))
                    .strokeColor(Color.BLACK).strokeWidth(2).fillColor(0x14C70000);

            Polygon polygon = googleMap.addPolygon(polygone);

            PolygonOptions polygone2 = new PolygonOptions()
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.0001,stationEntry.getValue().deg - 180 ), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.0001 ,stationEntry.getValue().deg - 180)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.0001 / 2 ,stationEntry.getValue().deg -90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.0001 /2 ,stationEntry.getValue().deg - 90)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.0001 / 2 ,stationEntry.getValue().deg - 180), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.0001 / 2  ,stationEntry.getValue().deg - 180)))
                    .add(new LatLng(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.0001 / 2 ,stationEntry.getValue().deg + 90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.0001 / 2 ,stationEntry.getValue().deg + 90)))
                    .strokeColor(Color.BLACK).strokeWidth(2).fillColor(0x1340ff40);

            Polygon polygon2 = googleMap.addPolygon(polygone2);
            //удаление полигона
            //polygon.remove();
        }
        //стрелки станций распространения ветра
//        double latstat = ((MainActivity)getActivity()).stationclose.latitude;
//        double lngstat = ((MainActivity)getActivity()).stationclose.longitude;
//
//        PolylineOptions polylineOptions1 = new PolylineOptions()
//                                            .add(new LatLng(latstat,lngstat))
//                                            .add(new LatLng(CalculateDistance.newlat(latstat,0.0048,210 - 180), CalculateDistance.newlng(latstat,lngstat,0.0048,210-180)))
//                                            .color(Color.RED)
//                                            .width(10);
//        googleMap.addPolyline(polylineOptions1);
//
//        PolylineOptions polylineOptions2 = new PolylineOptions()
//                .add(new LatLng(0,0))
//                .add(new LatLng(20,20))
//                .color(Color.BLUE)
//                .width(10);
//        googleMap.addPolyline(polylineOptions2);


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(56.488627, 84.995925),12));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                if(lastMarker != null){
                    lastMarker=null;
                }
            }
        });

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                return null;
            }

            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v =  inflater.inflate(R.layout.map_marker_info_window, null);
                lastMarker = marker;

                for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){

                    LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.mminfo);

                    if(stationEntry.getValue().marker.equals(marker)){

                        LinearLayout.LayoutParams layoutParamsName = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        layoutParamsName.gravity = Gravity.CENTER_HORIZONTAL;

                        TextView textViewName = new TextView(getContext());
                        if(stationEntry.getValue().name_station == null) {
                            textViewName.setText(Html.fromHtml("<font color='#6200EE'><b>Адрес: </b></font>"
                                    + stationEntry.getValue().majorName));
                        }
                        else {
                            textViewName.setText(Html.fromHtml("<font color='#6200EE'><b>Адрес: </b></font>"
                                    + stationEntry.getValue().majorName + ", " + stationEntry.getValue().name_station));
                        }
                        textViewName.setTextColor(0xFF000000);
                        textViewName.setLayoutParams(layoutParamsName);
                        linearLayout.addView(textViewName);

                        TextView textViewName2 = new TextView(getContext());
                        textViewName2.setText(Html.fromHtml("<font color='#6200EE'><b>Расстояние до станции: </b></font>"
                                + String.format("%.0f",CalculateDistance.distance(((MainActivity) getActivity()).myLat, ((MainActivity) getActivity()).myLong, stationEntry.getValue().latitude, stationEntry.getValue().longitude)) + " м."));
                        textViewName2.setTextColor(0xFF000000);
                        textViewName2.setLayoutParams(layoutParamsName);
                        linearLayout.addView(textViewName2);

                        TextView textViewName3 = new TextView(getContext());
                        textViewName3.setText(Html.fromHtml("<font color='#6200EE'><b>Ветер: </b></font>"
                                + CalculateDistance.windDrow(stationEntry.getValue().deg) + " " + String.format("%.0f",stationEntry.getValue().windSpeed) + " м/c"));
                        textViewName3.setTextColor(0xFF000000);
                        textViewName3.setLayoutParams(layoutParamsName);
                        linearLayout.addView(textViewName3);

                        LinearLayout linearLayoutStation = new LinearLayout(getContext());
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

                        TextView textViewLat = new TextView(getContext());
                        textViewLat.setText(Html.fromHtml("<font color='#6200EE'><b>Широта: </b></font>"
                                + String.format("%.2f", stationEntry.getValue().latitude)));
                        textViewLat.setTextColor(0xFF000000);
                        textViewLat.setLayoutParams(layoutParamsLat);
                        linearLayoutStation.addView(textViewLat);

                        TextView textViewLong = new TextView(getContext());
                        textViewLong.setText(Html.fromHtml("<font color='#6200EE'><b>Долгота: </b></font>"
                                + String.format("%.2f", stationEntry.getValue().longitude)));
                        textViewLong.setTextColor(0xFF000000);
                        textViewLong.setLayoutParams(layoutParamsLong);
                        linearLayoutStation.addView(textViewLong);

                        linearLayout.addView(linearLayoutStation);

                        for(Measurment measurment : stationEntry.getValue().measurments){

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

                            TextView textViewDisc = new TextView(getContext());
                            textViewDisc.setText(measurment.description);
                            textViewDisc.setTextColor(0xFF000000);
                            textViewDisc.setLayoutParams(layoutParamsDisc);
                            linearLayout.addView(textViewDisc);


                            LinearLayout linearLayoutCurr = new LinearLayout(getContext());
                            LinearLayout.LayoutParams layoutParamsCurr = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            linearLayoutCurr.setOrientation(LinearLayout.HORIZONTAL);
                            linearLayoutCurr.setLayoutParams(layoutParamsCurr);



                            TextView textView = new TextView(getContext());
                            textView.setText(measurment.value + " " + measurment.nameparameter);

                            textView.setTextColor(0xFF9100A1);

                            textView.setLayoutParams(layoutParams1);
                            linearLayoutCurr.addView(textView);

                            TextView textView2 = new TextView(getContext());

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

                            linearLayout.addView(linearLayoutCurr);
                        }
                    }


                }

                return v;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Toast.makeText(getContext(),"onInfoWindowClick", Toast.LENGTH_LONG).show();
                showDialog(marker);
            }
        });
    }

    public void showDialog(Marker marker) {

        CustomDialogFragment dialog = new CustomDialogFragment(marker, stationMap);
        dialog.show(getChildFragmentManager(), "custom");
        //dialog.show(getParentFragmentManager(),"test");
    }


}