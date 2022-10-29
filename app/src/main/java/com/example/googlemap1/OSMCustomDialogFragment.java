package com.example.googlemap1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.model.Marker;

import java.util.Map;

public class OSMCustomDialogFragment extends DialogFragment {

    private Station station;

    OSMCustomDialogFragment(Station station){
        this.station = station;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_marker_info_window, null);

            LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.mminfo);

                LinearLayout.LayoutParams layoutParamsName = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParamsName.gravity = Gravity.CENTER_HORIZONTAL;


                TextView textViewName = new TextView(getContext());
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
                linearLayout.addView(textViewName);

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
                        + String.format("%.2f", station.latitude)));
                textViewLat.setTextColor(0xFF000000);
                textViewLat.setPadding(100,0,0,0);
                textViewLat.setLayoutParams(layoutParamsLat);
                linearLayoutStation.addView(textViewLat);

                TextView textViewLong = new TextView(getContext());
                textViewLong.setText(Html.fromHtml("<font color='#6200EE'><b>Долгота: </b></font>"
                        + String.format("%.2f", station.longitude)));
                textViewLong.setTextColor(0xFF000000);
                textViewLong.setPadding(100,0,0,0);
                textViewLong.setLayoutParams(layoutParamsLong);
                linearLayoutStation.addView(textViewLong);

                linearLayout.addView(linearLayoutStation);

                for(Measurment measurment : station.measurments){

                    LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams1.weight = 1;

                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams2.weight = 3;

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
                    textView.setPadding(100,0,0,30);
                    textView.setLayoutParams(layoutParams1);
                    linearLayoutCurr.addView(textView);

                    TextView textView2 = new TextView(getContext());
                    if(measurment.recomendation == null){
                        textView2.setText("Нет рекомендаций");
                        textView2.setTextColor(0xFF007a05);
                    }
                    else
                    {
                        textView2.setText(measurment.recomendation);
                        textView2.setTextColor(0xFFcc0002);
                    }
                    textView2.setLayoutParams(layoutParams2);
                    linearLayoutCurr.addView(textView2);

                    linearLayout.addView(linearLayoutCurr);
                }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setView(v).create();
    }
}
