package com.example.googlemap1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InfoFragment extends Fragment {

    public ArrayList<ArrayGraphic> arrayGraphics = new ArrayList<ArrayGraphic>();

    public InfoFragment(){
        super(R.layout.fragment_info);
    }
//?
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_info, container, false);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.arrayGraphics = ((MainActivity) getActivity()).arrayGraphics;

        RecyclerView recyclerView = view.findViewById(R.id.listgraph);

        GraphicAdapter adapter = new GraphicAdapter(getContext(), arrayGraphics);
        recyclerView.setAdapter(adapter);

//        Calendar calendar = new GregorianCalendar(2017, 0 , 10);
//        Date d1 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d2 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d3 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d4 = calendar.getTime();
//
//        GraphView graph = (GraphView) view.findViewById(R.id.graph);
//
//// you can directly pass Date objects to DataPoint-Constructor
//// this will convert the Date to double via Date#getTime()
//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
//                new DataPoint(d1, 3.87),
//                new DataPoint(d2, 3.67),
//                new DataPoint(d3, 3.32),
//                new DataPoint(d4, 3.10)
//        });
//        graph.addSeries(series);
//
////        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date date = new Date(1212121212121L);
////
////        System.out.println(formater.format(date));
//
//
//// set date label formatter
//        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), new SimpleDateFormat("dd.MM.yyyy")));
//        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
//
//        series.setSpacing(70);
//
//// set manual x bounds to have nice steps
//        graph.getViewport().setMinX(d1.getTime());
//        graph.getViewport().setMaxX(d4.getTime());
//        graph.getViewport().setXAxisBoundsManual(true);
//
//// as we use dates as labels, the human rounding to nice readable numbers
//// is not necessary
//        graph.getGridLabelRenderer().setHumanRounding(false);


//        GraphView graph1 = (GraphView) view.findViewById(R.id.graph1);
//        LineGraphSeries<DataPoint> serieses = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph1.addSeries(serieses);

//        GraphView graph1 = (GraphView) view.findViewById(R.id.graph1);
//        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(d1, -10),
//                new DataPoint(d2, -20),
//                new DataPoint(d3, -5),
//                new DataPoint(d4, 2)
//        });
//        graph1.addSeries(series1);
//
//
//        graph1.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), new SimpleDateFormat("dd.MM.yyyy")));
//        graph1.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
//
//
//
//        graph1.getViewport().setMinX(d1.getTime());
//        graph1.getViewport().setMaxX(d4.getTime());
//        graph1.getViewport().setXAxisBoundsManual(true);
//
//        graph1.getGridLabelRenderer().setHumanRounding(false);
    }

//    private void setInitialData(){

//        graphics.add(new Graphic(1));
//        graphics.add(new Graphic(2));
//        graphics.add(new Graphic(3));
//        graphics.add(new Graphic(4));
//        graphics.add(new Graphic(5));

//    }
}