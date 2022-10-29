package com.example.googlemap1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {

    private final static String TAG = "MainActivity";

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;//, textView10;

    public Map<Integer, Station> stationMap = new HashMap<Integer, Station>();

    public HomeFragment(){
        Log.d(TAG, "HOME Constructor");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "HOME onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "HOME onCreate");
        //Initialize fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "HOME onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "HOME onViewCreated");
//        textView1 = (TextView) view.findViewById(R.id.text_view1);
//        textView2 = (TextView) view.findViewById(R.id.text_view2);
        textView3 = (TextView) view.findViewById(R.id.text_view3);
        textView4 = (TextView) view.findViewById(R.id.text_view4);
        textView5 = (TextView) view.findViewById(R.id.text_view5);
//        textView6 = (TextView) view.findViewById(R.id.text_view6);
        textView7 = (TextView) view.findViewById(R.id.text_view7);
        //textView10 = (TextView) view.findViewById(R.id.text_view10);

        RecyclerView recyclerView = view.findViewById(R.id.list);




        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Log.d(TAG, "HOME LOCATION SEARCH");
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        List<Address>addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude :</b><br></font>"
//                                + addresses.get(0).getLongitude()));
//                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude :</b><br></font>"
//                                + addresses.get(0).getLatitude()));
                        textView3.setText(Html.fromHtml("<font color='#0043EE'><b>Страна местонахождения:</b><br></font>"
                                + addresses.get(0).getCountryName()));
                        textView4.setText(Html.fromHtml("<font color='#0043EE'><b>Город местонахождения:</b><br></font>"
                                + addresses.get(0).getLocality()));
                        textView5.setText(Html.fromHtml("<font color='#0043EE'><b>Адрес:</b><br></font>"
                                + addresses.get(0).getAddressLine(0)));
                        //getStantions
                        stationMap = ((MainActivity) getActivity()).stationMap;

                        //получить близкую станцию

                        Double res = null;
                        Station stationclose = null;
                        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
                            double d = Math.sqrt(Math.pow(stationEntry.getValue().longitude - addresses.get(0).getLongitude(), 2) + Math.pow(stationEntry.getValue().latitude - addresses.get(0).getLatitude(), 2));
                            Log.d(TAG, "D1: " + d + " " + stationEntry.getValue().name_station + " " + stationEntry.getValue().stationID);
                            if(res == null){
                                res = d;
                                stationclose = stationEntry.getValue();
                            }
                            else {
                                if(d<res) {
                                    res = d;
                                    stationclose = stationEntry.getValue();
                                }
                            }

                            stationEntry.getValue().distance = CalculateDistance.distance(addresses.get(0).getLatitude(), addresses.get(0).getLongitude(), stationEntry.getValue().latitude, stationEntry.getValue().longitude);
                        }



                        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
                            if(stationclose.distance != 0){
                                if(stationclose.distance > stationEntry.getValue().distance)
                                    stationclose = stationEntry.getValue();
                            }
                            else {
                                stationclose = stationEntry.getValue();
                            }
                        }

                        ((MainActivity) getActivity()).stationclose = stationclose;
                        ((MainActivity) getActivity()).myLong = addresses.get(0).getLongitude();
                        ((MainActivity) getActivity()).myLat = addresses.get(0).getLatitude();

                        Log.d(TAG, "Близкая станция " + stationclose.stationID);
                        if (stationclose.name_station == null) {
                            textView7.setText(Html.fromHtml("<font color='#0043EE'><b>Ближайшая странция находится:</b><br></font>" +
                                    stationclose.majorName +
                                    "<br><br><font color='#0043EE'><b>Показания ближайшей станции:</b></font>"));
                        }
                        else {
                            textView7.setText(Html.fromHtml("<font color='#0043EE'><b>Ближайшая странция находится:</b><br></font>" +
                                    stationclose.majorName + " " + stationclose.name_station +
                                    "<br><br><font color='#0043EE'><b>Показания ближайшей станции: </b></font>"));
                        }
                        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llHome);
//                        for(Measurment measurment : stationclose.measurments){
//                            LinearLayout linearLayout1 = new LinearLayout(getContext());
//                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
//                            layoutParams.topMargin = 1;
//
//                            linearLayout1.setLayoutParams(layoutParams);
//                            linearLayout.addView(linearLayout1);
//
//                            TextView textView = new TextView(getContext());
//                            textView.setText(Html.fromHtml("<font color='#6200EE'><b>" + measurment.description +" :</b><br></font>"
//                                    + measurment.value + " " + measurment.nameparameter));
//                            textView.setPadding(1,1,1,1);
//                            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT);
//                            layoutParams2.weight = 1;
//                            textView.setLayoutParams(layoutParams2);
//
//                            linearLayout1.addView(textView);
//
//                            TextView textView2 = new TextView(getContext());
//                            if(measurment.recomendation == null) {
//                                textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Нет рекомендаций </b><br></font>"));
//                            }
//                            else {
//                                textView2.setText(Html.fromHtml("<font color='#ff4400'><b>" + measurment.recomendation + " </b><br></font>"));
//                            }
//                            textView2.setPadding(1,1,1,1);
//                            textView2.setLayoutParams(layoutParams2);
//
//                            linearLayout1.addView(textView2);
//                    }
                        //textView10.setText(Html.fromHtml("<font color='#04db00'><b>Нахождение на улице безопасно</b><br></font>"));

                        StateAdapter adapter = new StateAdapter(getContext(), stationclose.measurments);
                        recyclerView.setAdapter(adapter);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            }

        });

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "HOME onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "HOME onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "HOME onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "HOME onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "HOME onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "HOME onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "HOME onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

}