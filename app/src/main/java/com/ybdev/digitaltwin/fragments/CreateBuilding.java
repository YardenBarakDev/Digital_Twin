package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;


public class CreateBuilding extends Fragment {
    private static final String TAG = "CreateBuilding";
    private View view;
    private TextInputEditText building_LBL_name;
    private TextInputEditText building_LBL_address;
    private TextInputEditText building_LBL_floors;
    private TextInputEditText building_LBL_apartments;
    private MaterialButton    building_BTN_createBuilding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.create_building, container, false);
        }

        findViews(view);

        building_BTN_createBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building building = new Building();
                try {
                    building.setFloor(Integer.parseInt(building_LBL_floors.getText().toString()));
                    building.setName(building_LBL_name.getText().toString());
                    building.setNumOfWorkers(Integer.parseInt(building_LBL_apartments.getText().toString()));

                }catch (Exception e ){
                    Log.d(TAG, "onClick: "+ e.getMessage());
                    Toast.makeText(getContext(), "Please enter correct building", Toast.LENGTH_SHORT).show();
                }

                postBuilding(building); //TODO : add post method

                NavHostFragment.findNavController(CreateBuilding.this).navigate(R.id.action_createBuilding_to_buildingList);
            }
        });

        return view;
    }

    private void postBuilding(Building building) {
    }

    private void findViews(View view) {

        building_LBL_name = view.findViewById(R.id.building_LBL_name);
        building_LBL_address = view.findViewById(R.id.building_LBL_address);
        building_LBL_floors = view.findViewById(R.id.building_LBL_floors);
        building_LBL_apartments = view.findViewById(R.id.building_LBL_apartments);
        building_BTN_createBuilding = view.findViewById(R.id.building_BTN_createBuilding);


    }
}