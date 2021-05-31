package com.ybdev.digitaltwin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
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
            holder.project_LBL_name.setText("Id "+ temps.getID());
            holder.project_LBL_name2.setText("Num room" +temps.getNumOfRooms());
            holder.project_LBL_name3.setText("Price :"+temps.getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView project_LBL_name;
        private MaterialTextView project_LBL_name2;
        private MaterialTextView project_LBL_name3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(View itemView) {
            project_LBL_name = itemView.findViewById(R.id.project_LBL_name);
            project_LBL_name2 = itemView.findViewById(R.id.project_LBL_name2);
            project_LBL_name3 = itemView.findViewById(R.id.project_LBL_name3);
        }
    }
}
