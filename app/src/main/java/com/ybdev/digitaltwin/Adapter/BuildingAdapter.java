package com.ybdev.digitaltwin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.util.MySP;

import java.util.ArrayList;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private static final String TAG = "AdapterBuilding";
    private Context context;
    private ArrayList<Building> items;
    private Fragment fragment;

    public BuildingAdapter(Context context ,ArrayList<Building> items , Fragment fragment) {
        Log.d(TAG, "RecyclerViewAdapterBuilding: ");
        this.context = context;
        this.items = items;
        this.fragment = fragment;
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
            holder.card_building_LBL_name.setText("Id : " +temps.getName());
            holder.card_building_LBL_name2.setText("Total floor : " +temps.getFloor());
            holder.card_building_LBL_name3.setText("Appartment num : "+temps.getNumOfWorkers());
            holder.building_LAY_crd.setOnClickListener(view -> {
                MySP.getInstance().putString(MySP.KEYS.BUILDING,temps.getID());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_buildingList_to_apartmentList);
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView card_building_LBL_name;
        private MaterialTextView card_building_LBL_name2;
        private MaterialTextView card_building_LBL_name3;
        private MaterialCardView building_LAY_crd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(View itemView) {
            card_building_LBL_name = itemView.findViewById(R.id.card_building_LBL_name);
            card_building_LBL_name2 = itemView.findViewById(R.id.card_building_LBL_name2);
            card_building_LBL_name3 = itemView.findViewById(R.id.card_building_LBL_name3);
            building_LAY_crd = itemView.findViewById(R.id.building_LAY_crd);
        }
    }
}
