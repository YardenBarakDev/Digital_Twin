package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ybdev.digitaltwin.Adapter.BuildingAdapter;
import com.ybdev.digitaltwin.Adapter.ProjectAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BuildingList extends Fragment {

    protected View view;
    private Spinner Building_Spinner;
    private RecyclerView Building_RecyclerView;
    private FloatingActionButton Building_FAB_create_new_building;
    ArrayList<Building> allBuildings;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.building_list, container, false);
        }
        allBuildings = new ArrayList<>();
        allBuildings.add(new Building());

        findViews();

        // click listener. create new building
        Building_FAB_create_new_building.setOnClickListener(view -> {

            //change fragment to  create building
            NavHostFragment.findNavController(BuildingList.this).navigate(R.id.action_buildingList_to_createBuilding);
        });


        return view;
    }

    // TODO : call this method when after the api call
    private void setAdapter() {
        BuildingAdapter buildingAdapter = new BuildingAdapter(getContext() , allBuildings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Building_RecyclerView.setLayoutManager(linearLayoutManager);
        Building_RecyclerView.setAdapter(buildingAdapter);
    }

    private void findViews() {
        Building_Spinner = view.findViewById(R.id.Building_Spinner);
        Building_RecyclerView = view.findViewById(R.id.Building_RecyclerView);
        Building_FAB_create_new_building = view.findViewById(R.id.Building_FAB_create_new_building);
    }
}