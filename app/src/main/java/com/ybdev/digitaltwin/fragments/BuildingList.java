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
import android.widget.Toast;

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
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BuildingList extends Fragment {
    private static final String TAG = "BuildingList";
    protected View view;
    private TextView emptyListText;
    private Spinner Building_Spinner;
    private RecyclerView Building_RecyclerView;
    private FloatingActionButton Building_FAB_create_new_building;
    private ArrayList<Building> allBuildings;
    private String userEmail = MySP.getInstance().getString(MySP.KEYS.USER_EMAIL, "");


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.building_list, container, false);
        }
        findViews();

        String desiredId = MySP.getInstance().getString(MySP.KEYS.PROJECT, "projectString");
        Log.d(TAG, "onCreateView: id: " + desiredId);


        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchBuildingsFromServer(desiredId);
            }
        }).start();

        // click listener. create new building
        Building_FAB_create_new_building.setOnClickListener(view -> {
            //change fragment to  create building
            NavHostFragment.findNavController(BuildingList.this).navigate(R.id.action_buildingList_to_createBuilding);
        });


        return view;
    }


    private void fetchBuildingsFromServer(String projectId) {
        Log.d(TAG, "fetchBuildingsFromServer: " + projectId);

        String url = "http://192.168.43.243:8042/twins/operations";
        OkHttpClient okHttpClient = new OkHttpClient();

        String requestJson = "{\n" +
                "    \"operationId\": {\n" +
                "        \"space\": \"2021b.twins\",\n" +
                "        \"id\": \"451\"\n" +
                "    },\n" +
                "    \"type\": \"GET_BUILDINGS\",\n" +
                "    \"item\": {\n" +
                "        \"itemId\": {\n" +
                "            \"space\": \"2021b.vadim.kandorov\",\n" +
                "            \"id\": \"" + projectId + "\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"createdTimestamp\": \"2021-03-07T09:57:13.109+0000\",\n" +
                "    \"invokedBy\": {\n" +
                "        \"userId\": {\n" +
                "            \"space\": \"2021b.vadim.kandorov\",\n" +
                "            \"email\": \"Vadix3@gmail.com\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"operationAttributes\": {\n" +
                "        \"key1\": \"can be set to any value you wish\",\n" +
                "        \"key2\": {\n" +
                "            \"key2Subkey1\": \"can be nested json\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), requestJson);

        Request request2 = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request2);
        try {
            Response response = call.execute();
            Log.d(TAG, "postInfoToDb code: " + response.code());
            String responseBody = response.body().string();
            Log.d(TAG, "fetchBuildingsFromServer: " + responseBody);

            try {

                allBuildings = new ArrayList<>();

                JSONArray bArray = new JSONArray(responseBody);

                for (int i = 0; i < bArray.length(); i++) {

                    JSONObject temp = (JSONObject) bArray.get(i);
                    String attr = (String) temp.get("itemAttributes");
                    Log.d(TAG, "fetchBuildingsFromServer: " + attr);

                    Building tempBuilding = new Gson().fromJson(attr, Building.class);
                    Log.d(TAG, "fetchBuildingsFromServer: Object = " + tempBuilding.toString());

                    allBuildings.add(tempBuilding);
                    Log.d(TAG, "fetchBuildingsFromServer: ");
                }

                for (Building b : allBuildings) {
                    Log.d(TAG, "fetchBuildingsFromServer: " + b.toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            //probably 200
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Buildings Fetched Successfully!", Toast.LENGTH_SHORT).show();
                    setAdapter(allBuildings);
                }
            });
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }


    }

    private void setAdapter(ArrayList<Building> buildings) {
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