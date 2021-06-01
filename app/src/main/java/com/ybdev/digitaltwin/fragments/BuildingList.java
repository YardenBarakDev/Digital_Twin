package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.ybdev.digitaltwin.Adapter.BuildingAdapter;
import com.ybdev.digitaltwin.Adapter.ProjectAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;
import com.ybdev.digitaltwin.util.MySP;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BuildingList extends Fragment {
    private static final String TAG = "BuildingList";
    protected View view;
    private TextView emptyListText;
    private Spinner Building_Spinner;
    private RecyclerView Building_RecyclerView;
    private FloatingActionButton Building_FAB_create_new_building;
    ArrayList<Building> allBuildings;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.building_list, container, false);
        }
        findViews();

        String projectJson = MySP.getInstance().getString(MySP.KEYS.PROJECT, "");
        ConstructionProject project = new Gson().fromJson(projectJson, ConstructionProject.class);

        Log.d(TAG, "onCreateView: " + project.toString());

        if (project.getBuildings() == null || project.getBuildings().size() == 0) {
            Log.d(TAG, "onCreateView: null/empty array");
            emptyListText.setVisibility(View.VISIBLE);
        }else{
            Log.d(TAG, "onCreateView: Array has something");
            setAdapter(project.getBuildings());
        }

        // click listener. create new building
        Building_FAB_create_new_building.setOnClickListener(view -> {
            //change fragment to  create building
            NavHostFragment.findNavController(BuildingList.this).navigate(R.id.action_buildingList_to_createBuilding);
        });


        return view;
    }

    private void setAdapter(ArrayList<Building>buildings) {
        BuildingAdapter buildingAdapter = new BuildingAdapter(getContext(), buildings, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Building_RecyclerView.setLayoutManager(linearLayoutManager);
        Building_RecyclerView.setAdapter(buildingAdapter);
    }

    private void findViews() {
        emptyListText = view.findViewById(R.id.buildingList_LBL_emptyList);
        Building_Spinner = view.findViewById(R.id.Building_Spinner);
        Building_RecyclerView = view.findViewById(R.id.Building_RecyclerView);
        Building_FAB_create_new_building = view.findViewById(R.id.Building_FAB_create_new_building);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}