package com.example.googlemap1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //переменные запросов по получению ветра
    private final String urlapi = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "2633dfae5b9532c07036d02d62fd2cd4";
    RequestQueue mRequestQueue;

    private Timer mTimer;
    private MyTimerTask mMyTimerTask;


    private final static String TAG = "MainActivity";

    //public ArrayList<Sensor> sensors = new ArrayList<Sensor>();

    public Map<Integer,Station> stationMap = new HashMap<Integer, Station>();

    //public ArrayList<SensorData> stations = new ArrayList<SensorData>();

    public Station stationclose;
    public Double myLong;
    public Double myLat;

    public ArrayList<ArrayGraphic> arrayGraphics = new ArrayList<ArrayGraphic>();

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    private static String ip = "192.168.0.100";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "AirSystemDB";
    private static String username = "user1";
    private static String password = "sa";
    private static String url = "jdbc:jtds:sqlserver://" + ip +":" + port + "/" + database;

    private Connection connection = null;

    DrawerLayout drawerLayout;
    ImageView drawerMenu;
    LinearLayout menuMap, menuMap2, menuHome, menuInfo, menuGraph, menuSettings, menuLogout, menuWeather;
    TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarText = findViewById(R.id.toolbar_text);

        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);

        drawerMenu = findViewById(R.id.drawer_menu);
        drawerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        menuMap = findViewById(R.id.menu_map);
        menuMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toolbarText.setText("Карта");

                if(prefs.getString("choseMap", "null").equals("GMap"))
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MapFragment()).commit();

                if((prefs.getString("choseMap", "null")).equals("OSMMap"))
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new OsmdroidFragment()).commit();

                onBackPressed();
            }
        });

//        menuMap2 = findViewById(R.id.menu_map2);
//        menuMap2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toolbarText.setText("Карта");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new OsmdroidFragment()).commit();
//                onBackPressed();
//            }
//        });

        menuHome = findViewById(R.id.menu_home);
        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarText.setText("Главная");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                onBackPressed();
            }
        });

        menuWeather = findViewById(R.id.openweather);
        menuWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarText.setText("Погода");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new WeatherFragment()).commit();
                onBackPressed();
            }
        });

        menuInfo = findViewById(R.id.menu_info);
        menuInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarText.setText("Инфо");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new InfoPageFragment()).commit();
                onBackPressed();
            }
        });


        menuGraph = findViewById(R.id.menu_graph);
        menuGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarText.setText("Графики");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new InfoFragment()).commit();
                onBackPressed();
            }
        });

        menuSettings = findViewById(R.id.menu_settings);
        menuSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarText.setText("Настройки");

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SettingsFragment()).commit();
                onBackPressed();
            }
        });

        menuLogout = findViewById(R.id.menu_logout);
        menuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Выход");
                builder.setMessage("Вы уверены что хотите выйти ?");

                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.moveTaskToBack(true);
                    }
                });

                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                    }
                });

                onBackPressed();
                builder.show();
            }
        });

        //Initialize fragment
        Fragment fragment = new HomeFragment();


        //Open fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();


//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                Fragment selectedFragment = null;
//
//                switch (item.getItemId()){
//                    case R.id.homeFragment:
//                        selectedFragment = new HomeFragment();
//                        break;
//                    case R.id.mapFragment:
//                        selectedFragment = new MapFragment();
//                        break;
//                    case R.id.infoFragment:
//                        selectedFragment = new InfoFragment();
//                        break;
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
//                return false;
//            }
//        });

        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            Toast.makeText(this, "Доступ местоположения предоставлен", Toast.LENGTH_SHORT).show();
        } else {
            //when permission denied
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            //textView.setText("SUCCESS");
            Log.d(TAG, "Connection SUCCESS");
            sqlButton();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //textView.setText("ERROR");
            Log.d(TAG, "Connection ERRORR");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //textView.setText("FAILURE");
            Log.d(TAG, "Connection FAILURE");
        }

        mTimer = new Timer();
        mMyTimerTask = new MyTimerTask();
        mTimer.schedule(mMyTimerTask, 1000, 10000);




        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(false)
                        .setSmallIcon(R.drawable.ic_warning)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Опасный показатель! Есть рекомендации!  ")
                        .setContentText("Атмосферное давление в Гектопаскалях 1011 hPa");

        createChannelIfNeeded(notificationManager);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());


        mRequestQueue = Volley.newRequestQueue(this);
        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
            String url = this.urlapi + "?lat=" + stationEntry.getValue().latitude + "&lon=" + stationEntry.getValue().longitude + "&appid=" + appid;
            getWeather(stationEntry.getValue(), url);
        }

    }

//    public void onClickTest(View view) {
//        Fragment fragment = new Fragment();
//        switch (view.getId()){
//            case R.id.button2:
//                fragment = new MapFragment();
//                break;
//
//            case R.id.button4:
//                fragment = new InfoFragment();
//                break;
//        }
//
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_layout, fragment)
//                .commit();
//    }


    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }


    public void sqlButton(){
        if(connection!=null){

            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT StationID" +
                        "      ,Latitude" +
                        "      ,Longitude" +
                        "      ,Name" +
                        "      ,CatchValue" +
                        "      ,Recommendation" +
                        "      ,TakingDateTime" +
                        "      ,NameParameter" +
                        "      ,Description" +
                        "      ,MajorName" +
                        "  FROM LastMeasurement_v2");

                for (Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
                    stationEntry.getValue().measurments.clear();
                }

                while (resultSet.next()){
                    //SensorData station = new SensorData();
                    //Log.d(TAG, resultSet.getString("Value"));
                    if(stationMap.containsKey(resultSet.getInt("StationID")))
                    {
                        Log.d(TAG,"Уже существует " + resultSet.getString("StationID"));
                        Measurment measurment = new Measurment();
                        measurment.value = resultSet.getDouble("CatchValue");
                        measurment.recomendation = resultSet.getString("Recommendation");
                        measurment.takingdatetime = resultSet.getDate("TakingDateTime");
                        measurment.nameparameter = resultSet.getString("NameParameter");
                        measurment.description = resultSet.getString("Description");
                        Station station1 = stationMap.get(resultSet.getInt("StationID"));
                        (station1.measurments).add(measurment);

                    }
                    else {
                        Station station = new Station();
                        station.stationID = resultSet.getInt("StationID");
                        station.latitude = resultSet.getDouble("Latitude");
                        station.longitude = resultSet.getDouble("Longitude");
                        station.name_station = resultSet.getString("Name");
                        station.majorName = resultSet.getString("MajorName");
                        Measurment measurment = new Measurment();
                        measurment.value = resultSet.getDouble("CatchValue");
                        measurment.recomendation = resultSet.getString("Recommendation");
                        measurment.takingdatetime = resultSet.getDate("TakingDateTime");
                        measurment.nameparameter = resultSet.getString("NameParameter");
                        measurment.description = resultSet.getString("Description");
                        (station.measurments).add(measurment);
                        stationMap.put(resultSet.getInt("StationID"), station);
                    }
                }

                statement = connection.createStatement();
                ResultSet resultSetTemp = statement.executeQuery("SELECT TOP (4) [Temperature]\n" +
                        "      ,[Date]" +
                        "  FROM [AirSystemDB].[dbo].[LastTemperatureForDate_v2]");

                ArrayList<Graphic> graphics = new ArrayList<Graphic>();
                arrayGraphics.clear();
                while (resultSetTemp.next()){
                    graphics.add(new Graphic(resultSetTemp.getDate("Date"),resultSetTemp.getDouble("Temperature")));
                }
                ArrayGraphic arrayGraphic = new ArrayGraphic();
                Collections.sort(graphics);
                arrayGraphic.graphics = graphics;
                arrayGraphic.ParametrType = 1;
                arrayGraphic.NameGraphic = "Средняя температура (C)";
                arrayGraphic.NameParameter = "Градусы";
                arrayGraphic.NameX = "Дата";
                arrayGraphic.NameY = "Температура";
                arrayGraphic.Descrip = "Средняя или точечная температура воздуха, измеренная на поверхности земли или воды или рядом с поверхностью земли или воды в градусах Цельсия";
                arrayGraphics.add(arrayGraphic);


                statement = connection.createStatement();
                ResultSet resultSetHumidity = statement.executeQuery("SELECT TOP (4) [Humidity]" +
                        "      ,[Date]" +
                        "  FROM [AirSystemDB].[dbo].[LastHumidityForDate]");

                graphics = new ArrayList<Graphic>();
                while (resultSetHumidity.next()){
                    graphics.add(new Graphic(resultSetHumidity.getDate("Date"),resultSetHumidity.getDouble("Humidity")));
                }
                arrayGraphic = new ArrayGraphic();
                Collections.sort(graphics);
                arrayGraphic.graphics = graphics;
                arrayGraphic.ParametrType = 2;
                arrayGraphic.NameGraphic = "Среднее значение отностельной влажности (%)";
                arrayGraphic.NameParameter = "% Влажности";
                arrayGraphic.NameX = "Дата";
                arrayGraphic.NameY = "% Влажности";
                arrayGraphic.Descrip = "Влажность воздуха, содержание водяного пара в воздухе, характеризуемое рядом величин. Вода, испарившаяся с поверхности материков и океанов при их нагревании, попадает в атмосферу и сосредотачивается в нижних слоях тропосферы.";
                arrayGraphics.add(arrayGraphic);


                statement = connection.createStatement();
                ResultSet resultSetPress = statement.executeQuery("SELECT TOP (4) [Press]" +
                        "      ,[Date]" +
                        "  FROM [AirSystemDB].[dbo].[LastPressForDate]");

                graphics = new ArrayList<Graphic>();
                while (resultSetPress.next()){
                    graphics.add(new Graphic(resultSetPress.getDate("Date"),resultSetPress.getDouble("Press")));
                }
                arrayGraphic = new ArrayGraphic();
                Collections.sort(graphics);
                arrayGraphic.graphics = graphics;
                arrayGraphic.ParametrType = 3;
                arrayGraphic.NameGraphic = "Среднее значение атмосферного давления (hPa)";
                arrayGraphic.NameParameter = "hPa давление";
                arrayGraphic.NameX = "Дата";
                arrayGraphic.NameY = "Давление";
                arrayGraphic.Descrip = "Давление воздуха, сила, с которой атмосферный воздух давит на поверхность земного шара и всех вообще тел, соприкасающихся с воздухом, измеряется в Гектопаскалях";
                arrayGraphics.add(arrayGraphic);


                statement = connection.createStatement();
                ResultSet resultSetAQI = statement.executeQuery("SELECT TOP (1000) [AQI]" +
                        "      ,[Date]" +
                        "  FROM [AirSystemDB].[dbo].[LastAQIForDate]");

                graphics = new ArrayList<Graphic>();
                while (resultSetAQI.next()){
                    graphics.add(new Graphic(resultSetAQI.getDate("Date"),resultSetAQI.getDouble("AQI")));
                }
                arrayGraphic = new ArrayGraphic();
                Collections.sort(graphics);
                arrayGraphic.graphics = graphics;
                arrayGraphic.ParametrType = 4;
                arrayGraphic.NameGraphic = "Среднее значение инд. качества воздуха (AQI)";
                arrayGraphic.NameParameter = "Знач. AQI";
                arrayGraphic.NameX = "Дата";
                arrayGraphic.NameY = "Качество воздуха";
                arrayGraphic.Descrip = "Индекс качества воздуха, эта аббревиатура используется во всех мировых экологический государственных органах для информирования общественности про уровень загрязнения воздуха а так же прогнозирование загрязнение воздуха.";
                arrayGraphics.add(arrayGraphic);



            } catch (SQLException e){
                e.printStackTrace();
                Log.d(TAG, e.toString());
            }

            Toast.makeText(MainActivity.this, "Получены данные", Toast.LENGTH_SHORT).show();
        }

        else {
            //textView.setText("Connection is null");
        }

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Выход");
            builder.setMessage("Вы уверены что хотите выйти ?");

            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    MainActivity.this.moveTaskToBack(true);
                }
            });

            builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();
        }
    }


    private void getWeather(Station station, String url){
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject wind = response.getJSONObject("wind");
                    station.deg = wind.getDouble("deg");
                   // station.windSpeed = wind.getDouble("speed");
                    station.windSpeed = 1.0;
                    station.nameAPI = response.getString("name");
                    Log.d(TAG, station.deg + " " + station.windSpeed + " " + station.nameAPI);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//
//            try {
//                Class.forName(Classes);
//                connection = DriverManager.getConnection(url, username, password);
//                //textView.setText("SUCCESS");
//                Log.d(TAG, "Connection SUCCESS");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                //textView.setText("ERROR");
//                Log.d(TAG, "Connection ERRORR");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                //textView.setText("FAILURE");
//                Log.d(TAG, "Connection FAILURE");
//            }

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    sqlButton();
                }
            });
        }
    }

}

