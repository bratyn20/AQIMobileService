package com.example.googlemap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Measurment> measurments;

    StateAdapter(Context context, List<Measurment> measurments) {
        this.measurments = measurments;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        Measurment measurment = measurments.get(position);

            if(measurment.recomendation == null){
                holder.flagView.setImageResource(R.drawable.ic_ok);
                holder.capitalView.setText("Нет рекомендаций");
            }
            else {
                holder.flagView.setImageResource(R.drawable.ic_warning);
                holder.capitalView.setText(measurment.recomendation);
            }

        holder.nameView.setText(measurment.value + " " +measurment.nameparameter);
        holder.discribeView.setText(measurment.description);
    }

    @Override
    public int getItemCount() {
        return measurments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView, capitalView, discribeView;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.flag);
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.capital);
            discribeView = view.findViewById(R.id.discribe);
        }
    }
}
