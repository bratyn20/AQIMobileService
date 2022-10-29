package com.example.googlemap1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OsmdroidFragment extends Fragment {

    private final static String TAG = "MainActivity";


    MapView map = null;
    TextView textDistance;

    public Map<Integer,Station> stationMap = new HashMap<Integer, Station>();;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.osmdroid_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stationMap = ((MainActivity)getActivity()).stationMap;

        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));

        map = (MapView) view.findViewById(R.id.mapview);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(((MainActivity) getActivity()).myLat,((MainActivity) getActivity()).myLong);
//        Marker startMarker = new Marker(map);
//        startMarker.setPosition(startPoint);
//        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//        map.getOverlays().add(startMarker);
        IMapController mapController = map.getController();
        mapController.setZoom(13.0);
        mapController.setCenter(startPoint);

        final CompassOverlay compassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), map);
        compassOverlay.enableCompass();
        map.getOverlays().add(compassOverlay);

//        Marker marker = new Marker(map);
//        marker.setPosition(new GeoPoint(56.4977100, 84.9743700));
//        marker.setIcon(getResources().getDrawable(R.drawable.ic_warning));
//        //marker.setImage(getResources().getDrawable(R.drawable.ic_launcher_foreground));
//        marker.setTitle("Marker");
//        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//        InfoWindow infoWindow = new OSMInfoWindow(R.layout.map_marker_info_window, map);
//        marker.setInfoWindow(infoWindow);
//        marker.showInfoWindow();
//        map.getOverlays().add(marker);

        Marker mylocationmarker = new Marker(map);
        mylocationmarker.setPosition(new GeoPoint(((MainActivity) getActivity()).myLat,((MainActivity) getActivity()).myLong));
        mylocationmarker.setIcon(getResources().getDrawable(R.drawable.ic_mylocation));
        mylocationmarker.setTitle("Моё местоположение");
        mylocationmarker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_BOTTOM);
        mylocationmarker.setInfoWindow(null);
        map.getOverlays().add(mylocationmarker);

//        List<GeoPoint> geoPoints = new ArrayList<>();
//        geoPoints.add(new GeoPoint(((MainActivity) getActivity()).myLat,((MainActivity) getActivity()).myLong));
//        geoPoints.add(new GeoPoint(((MainActivity) getActivity()).myLat + 0.5,((MainActivity) getActivity()).myLong + 0.5));
//        //add your points here
//        Polygon polygon = new Polygon();    //see note below
//        polygon.setFillColor(Color.argb(75, 255,0,0));
//        geoPoints.add(geoPoints.get(0));    //forces the loop to close
//        polygon.setPoints(geoPoints);
//        polygon.setTitle("A sample polygon");
//        map.getOverlays().add(polygon);


        List<Polygon> polygonsList = new ArrayList<>();
        for(Map.Entry<Integer, Station> stationEntry : stationMap.entrySet()){
            Marker marker1 = new Marker(map);
            marker1.setPosition(new GeoPoint(stationEntry.getValue().latitude, stationEntry.getValue().longitude));
            marker1.setIcon(getResources().getDrawable(R.drawable.ic_warning));
            marker1.setTitle(stationEntry.getValue().name_station);
            marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);


            List<GeoPoint> geoPoints1 = new ArrayList<>();
            geoPoints1.add(new GeoPoint(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.001 * 60,stationEntry.getValue().deg - 180 ), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.001 * 60 ,stationEntry.getValue().deg - 180)));
            geoPoints1.add(new GeoPoint(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.001 / 2 * 30,stationEntry.getValue().deg -90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.001 /2 * 30,stationEntry.getValue().deg - 90)));
            geoPoints1.add(new GeoPoint(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.001 / 2 * 60,stationEntry.getValue().deg - 180), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.001 / 2 * 60 ,stationEntry.getValue().deg - 180)));
            geoPoints1.add(new GeoPoint(CalculateDistance.newlat(stationEntry.getValue().latitude, stationEntry.getValue().windSpeed * 0.001 / 2 * 30,stationEntry.getValue().deg + 90), CalculateDistance.newlng(stationEntry.getValue().latitude,stationEntry.getValue().longitude,stationEntry.getValue().windSpeed * 0.001 / 2 * 30,stationEntry.getValue().deg + 90)));
            Polygon polygon1 = new Polygon();    //see note below
            polygon1.setFillColor(Color.argb(75, 255,0,0));
            geoPoints1.add(geoPoints1.get(0));    //forces the loop to close
            polygon1.setPoints(geoPoints1);
            polygon1.setTitle("A sample polygon");
//            map.getOverlays().add(polygon1);
            polygonsList.add(polygon1);

            //add line расстояние
            List<GeoPoint> geoPoints2 = new ArrayList<>();
            geoPoints2.add(new GeoPoint(((MainActivity)getActivity()).stationclose.latitude, ((MainActivity)getActivity()).stationclose.longitude ));
            geoPoints2.add(new GeoPoint(((MainActivity)getActivity()).myLat, ((MainActivity)getActivity()).myLong));
            Polygon polygon2 = new Polygon();    //see note below
            //polygon2.setFillColor(Color.rgb(255,1,1));
            polygon2.setStrokeColor(Color.RED);
            geoPoints2.add(geoPoints2.get(0));    //forces the loop to close
            polygon2.setPoints(geoPoints2);
//            map.getOverlays().add(polygon1);
            polygonsList.add(polygon2);
            map.getOverlays().add(polygon2);


            InfoWindow infoWindow = new OSMInfoWindow(R.layout.map_marker_info_window, map, stationEntry.getValue(), polygon1, getChildFragmentManager());
            marker1.setInfoWindow(infoWindow);
            //marker1.showInfoWindow();
            map.getOverlays().add(marker1);

        }

        textDistance = view.findViewById(R.id.text_distance2);
        double stationclose = CalculateDistance.distance(((MainActivity) getActivity()).myLat, ((MainActivity) getActivity()).myLong, ((MainActivity) getActivity()).stationclose.latitude, ((MainActivity) getActivity()).stationclose.longitude);
        textDistance.setText("Расстояние до ближайшей станции " + String.format("%.0f",stationclose) + " м.");

        map.getOverlayManager().add(new Overlay() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e, MapView mapView) {
                if(!InfoWindow.getOpenedInfoWindowsOn(mapView).isEmpty())
                {
                InfoWindow.closeAllInfoWindowsOn(map);
                }
                else {
                    for(Polygon polygon: polygonsList){
                        map.getOverlays().remove(polygon);
                    }
                }

                return super.onSingleTapConfirmed(e, mapView);
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));

    }
}
