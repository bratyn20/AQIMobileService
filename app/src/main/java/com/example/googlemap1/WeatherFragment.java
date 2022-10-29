package com.example.googlemap1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment {

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cityField = (TextView)view.findViewById(R.id.city_field);
        updatedField = (TextView)view.findViewById(R.id.updated_field);
        detailsField = (TextView)view.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)view.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)view.findViewById(R.id.weather_icon);

        cityField.setText("Tomsk");
        updatedField.setText("Tomskaya oblast', RU");
        detailsField.setText("Влажность: 41%\r\n" +
                "Ветер: ЮЗ 1.0м/c\r\n" +
                "Давление: 1002hPa\r\n" +
                "");
        currentTemperatureField.setText("14 °C");
        //weatherIcon.setText("5555");


    }
}
