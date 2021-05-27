package com.ybdev.digitaltwin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Building;

import java.util.ArrayList;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    private static final String TAG = "AdapterBuilding";
    private Context context;
    private ArrayList<Apartment> items;

    public ApartmentAdapter(Context context ,ArrayList<Apartment> items) {
        Log.d(TAG, "RecyclerViewAdapterBuilding: ");
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ApartmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_building, parent, false);
        return  new ApartmentAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentAdapter.ViewHolder holder, int position) {
        Apartment temps = items.get(position);
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
