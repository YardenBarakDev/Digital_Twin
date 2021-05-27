package com.ybdev.digitaltwin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;

import java.util.ArrayList;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private static final String TAG = "AdapterBuilding";
    private Context context;
    private ArrayList<Building> items;

    public BuildingAdapter(Context context ,ArrayList<Building> items) {
        Log.d(TAG, "RecyclerViewAdapterBuilding: ");
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BuildingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_building, parent, false);
        return  new BuildingAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BuildingAdapter.ViewHolder holder, int position) {
        Building temps = items.get(position);
        if(holder != null){

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
