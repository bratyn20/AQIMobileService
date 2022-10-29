package com.example.googlemap1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GraphicAdapter extends RecyclerView.Adapter<GraphicAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ArrayGraphic> arrayGraphics;

    GraphicAdapter(Context context, List<ArrayGraphic> arrayGraphics) {
        this.arrayGraphics = arrayGraphics;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public GraphicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_graph, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GraphicAdapter.ViewHolder holder, int position) {
        ArrayGraphic arrayGraphic = arrayGraphics.get(position);

        DataPoint[] dataPoint = new DataPoint[arrayGraphic.graphics.size()];

        int i = 0;
        for(Graphic graphic: arrayGraphic.graphics) {
//            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//            Date date = null;
//            try {
//                date = df.parse(graphic.x);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Calendar cal = new GregorianCalendar();
//            cal.setTime(date);
//            dataPoint[i] = new DataPoint((Date) date.clone(),graphic.y);
            dataPoint[i] = new DataPoint(graphic.x, graphic.y);

            i++;
        }

        holder.graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(holder.graphView.getContext(), new SimpleDateFormat("dd.MM.yyyy")));
        holder.graphView.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
//
//        if(arrayGraphic.ParametrType == 2 || arrayGraphic.ParametrType == 4 ) {
//            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoint);
//            series.setColor(Color.RED);
//            holder.graphView.addSeries(series);
//
//        }
//        else {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoint);

            holder.graphView.addSeries(series);

            //set color, title of curve, DataPoints radius, thickness
            series.setColor(Color.RED);
            series.setTitle(arrayGraphic.NameParameter);
            //series.setDrawDataPoints(true);
            //series.setDataPointsRadius(16);
            series.setThickness(8);

            //set TitleGraph
            holder.graphView.setTitle(arrayGraphic.NameGraphic);
            holder.graphView.setTitleTextSize(45);
            holder.graphView.setTitleColor(Color.BLUE);

            //legend
            holder.graphView.getLegendRenderer().setVisible(true);
            holder.graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

            //axis titles
            GridLabelRenderer gridLabelRenderer = holder.graphView.getGridLabelRenderer();
            gridLabelRenderer.setHorizontalAxisTitle(arrayGraphic.NameX);
            gridLabelRenderer.setHorizontalAxisTitleTextSize(26);
            gridLabelRenderer.setVerticalAxisTitle(arrayGraphic.NameY);
            gridLabelRenderer.setVerticalAxisTitleTextSize(26);

            holder.descGraph.setText(arrayGraphic.Descrip);
      //  }

    }

    @Override
    public int getItemCount() {
        return arrayGraphics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final GraphView graphView;
        final TextView descGraph;
        ViewHolder(View view){
            super(view);
            graphView = (GraphView) view.findViewById(R.id.graph);
            descGraph = (TextView) view.findViewById(R.id.descGraph);

        }
    }

}
