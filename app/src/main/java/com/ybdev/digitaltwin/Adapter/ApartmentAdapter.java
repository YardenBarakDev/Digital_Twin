package com.ybdev.digitaltwin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.util.MySP;

import java.util.ArrayList;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    private static final String TAG = "AdapterApartment";
    private Context context;
    private ArrayList<Apartment> items;
    private Fragment fragment;

    public ApartmentAdapter(Context context ,ArrayList<Apartment> items,  Fragment fragment) {
        Log.d(TAG, "RecyclerViewAdapterApartment: ");
        this.context = context;
        this.items = items;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ApartmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_appartment, parent, false);
        return  new ApartmentAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentAdapter.ViewHolder holder, int position) {
        Apartment temps = items.get(position);
        if(holder != null){
            holder.project_LBL_name.setText("Id "+ temps.getID());
            holder.project_LBL_name2.setText("Num room" +temps.getNumOfRooms());
            holder.project_LBL_name3.setText("Price :"+temps.getPrice());
            holder.apartment_LAY_crd.setOnClickListener(view -> {
                MySP.getInstance().putString(MySP.KEYS.APARTMENT,temps.getID());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_apartmentList_to_createApartment);
            });
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
        private MaterialCardView apartment_LAY_crd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(View itemView) {
            project_LBL_name = itemView.findViewById(R.id.project_LBL_name);
            project_LBL_name2 = itemView.findViewById(R.id.project_LBL_name2);
            project_LBL_name3 = itemView.findViewById(R.id.project_LBL_name3);
            apartment_LAY_crd = itemView.findViewById(R.id.apartment_LAY_crd);
        }
    }
}
