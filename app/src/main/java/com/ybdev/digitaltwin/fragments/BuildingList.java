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
        getInfoFromServer();

        findViews();

        // click listener. create new building
        Building_FAB_create_new_building.setOnClickListener(view -> {
            //change fragment to  create building
            NavHostFragment.findNavController(BuildingList.this).navigate(R.id.action_buildingList_to_createBuilding);
        });


        return view;
    }

    private void getInfoFromServer() {
        String url = "http://192.168.1.202:8042/twins/items/2021b.vadim.kandorov/dima@notfound.com/ConstructionProject/"+ MySP.getInstance().getString(MySP.KEYS.PROJECT, "");

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String initialResponse = response.body().string();
                Gson gson = new Gson();
                try {
                    JSONArray jsonArray = new JSONArray(initialResponse);

                    Log.d(TAG, "onResponse: "+jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String  itemAttributes = jsonObject.get("type").toString();
                        if(itemAttributes.equals("Building")){
                            JSONObject itemobj = (JSONObject) jsonObject.get("itemAttributes");
                            Building building = gson.fromJson(String.valueOf(itemobj), Building.class);
                            allBuildings.add(building);
                        }
                        Log.d(TAG, "onResponse:" + itemAttributes.toString() );
                    }

                } catch (JSONException e) {
                    Log.d(TAG, "onResponse: faild "+e.getMessage());
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: "+allBuildings.toString());
                        setAdapter();
                    }
                });
                Log.d(TAG, "onResponse: Got response: " + initialResponse);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("jjjj", "onFailure: Exception: " + e.getMessage());
            }

        });

    }

    private void setAdapter() {
        BuildingAdapter buildingAdapter = new BuildingAdapter(getContext() , allBuildings , this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Building_RecyclerView.setLayoutManager(linearLayoutManager);
        Building_RecyclerView.setAdapter(buildingAdapter);
    }

    private void findViews() {
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