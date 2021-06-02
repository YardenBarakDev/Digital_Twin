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
import com.google.gson.Gson;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;
import com.ybdev.digitaltwin.items.objects.Facility;
import com.ybdev.digitaltwin.util.MySP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CreateBuilding extends Fragment {
    private static final String TAG = "CreateBuilding";
    private View view;
    private TextInputEditText building_LBL_name;
    private TextInputEditText building_LBL_address;
    private TextInputEditText building_LBL_floors;
    private TextInputEditText building_LBL_apartments;
    private MaterialButton building_BTN_createBuilding;
    private String itemSpace = "2021b.vadim.kandorov";

    private String userEmail = MySP.getInstance().getString(MySP.KEYS.USER_EMAIL, "");
    private String projectId = MySP.getInstance().getString(MySP.KEYS.PROJECT, "");

    private ConstructionProject project;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.create_building, container, false);
        }

//        String projectString = MySP.getInstance().getString(MySP.KEYS.PROJECT, "");
//        project = new Gson().fromJson(projectString, ConstructionProject.class);

//        Log.d(TAG, "onCreateView: Got project: " + project.toString());

        findViews(view);

        building_BTN_createBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building building = new Building();
                try {
                    building.setParentID(projectId);
                    building.setFloor(Integer.parseInt(building_LBL_floors.getText().toString()));
                    building.setID(UUID.randomUUID().toString());
                    building.setNumOfWorkers(Integer.parseInt(building_LBL_apartments.getText().toString()));
                    building.setName(building_LBL_name.getText().toString());
                    building.setReady(true);
                    building.setApartments(new ArrayList<Apartment>());
                    building.setFacilities(new ArrayList<Facility>());
                } catch (Exception e) {
                    Log.d(TAG, "onClick: " + e.getMessage());
                    Toast.makeText(getContext(), "Please enter correct building", Toast.LENGTH_SHORT).show();
                }
//                addBuildingToProject(building);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        addBuildingToDB(building);
                    }
                }).start();
            }
        });

        return view;
    }


    private void addBuildingToDB(Building building) {
        Log.d(TAG, "addBuildingToDB: " + building.toString());
        String url = "http://192.168.43.243:8042/twins/items/2021b.vadim.kandorov/" + userEmail;

        OkHttpClient okHttpClient = new OkHttpClient();

        Gson gson = new Gson();

        String details = gson.toJson(building);

        Log.d(TAG, "postInfoToDb: " + details);

        String json = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            json = "{\n" +
                    "    \"type\": \"Building\",\n" +
                    "    \"name\": \"Building " + building.getID() + " \",\n" +
                    "    \"active\": true,\n" +
                    "    \"createdTimestamp\": \"" + java.time.LocalDateTime.now() + "\",\n" +
                    "    \"createdBy\": {\n" +
                    "        \"userId\": {\n" +
                    "            \"space\": \"2021b.vadim.kandorov\",\n" +
                    "            \"email\": \"" + userEmail + "\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"location\": {\n" +
                    "\"lat\":32.115139,\n" +
                    "\"lng\":34.817804\n" +
                    "},\n" +
                    "    \"itemAttributes\":" + details + ",\n" +
                    "    \"itemId\": {\n" +
                    "        \"space\": \"2021b.vadim.kandorov\",\n" +
                    "        \"id\": \"" + building.getID() + "\"\n" +
                    "    }\n" +
                    "}";
        }

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request2 = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request2);
        try {
            Response response = call.execute();
            Log.d(TAG, "postInfoToDb: " + response.code());

            //probably 200
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Building Added Successfully!", Toast.LENGTH_SHORT).show();
                    goBackToBuildingList();
                }
            });
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }


    }


    private void addBuildingToProject(Building building) {
        Log.d(TAG, "addBuildingToProject: Got building: " + building.toString());
        project.getBuildings().add(building);

        //Update project inside SP with new building added
        Gson gson = new Gson();
        String projectString = gson.toJson(project);// Store the project in Json
        MySP.getInstance().putString(MySP.KEYS.PROJECT, projectString);


        new Thread(new Runnable() {
            @Override
            public void run() {
                updateProjectInDB();
            }
        }).start();
    }


    private void updateProjectInDB() {

        //  /twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}

        String url = "http://192.168.43.243:8042/twins/items/2021b.vadim.kandorov/vadix3@gmail.com/" +
                project.getId() + "/" + itemSpace;

        Log.d(TAG, "updateProjectInDB: URL = " + url);

        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();
        String details = gson.toJson(project);

        Log.d(TAG, "postInfoToDb: " + details);

        String json = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            json = "{\n" +
                    "    \"type\": \"ConstructionProject\",\n" +
                    "    \"name\": \"demo item " + project.getId() + " \",\n" +
                    "    \"active\": true,\n" +
                    "    \"createdTimestamp\": \"" + java.time.LocalDateTime.now() + "\",\n" +
                    "    \"createdBy\": {\n" +
                    "        \"userId\": {\n" +
                    "            \"space\": \"2021b.vadim.kandorov\",\n" +
                    "            \"email\": \"" + userEmail + "\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"location\": {\n" +
                    "\"lat\":32.115139,\n" +
                    "\"lng\":34.817804\n" +
                    "},\n" +
                    "    \"itemAttributes\":" + details + ",\n" +
                    "    \"itemId\": {\n" +
                    "        \"space\": \"2021b.vadim.kandorov\",\n" +
                    "        \"id\": \"1\"\n" +
                    "    }\n" +
                    "}";
        }

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request2 = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Call call = okHttpClient.newCall(request2);
        try {
            Response response = call.execute();

            Log.d(TAG, "postInfoToDb: " + response.code());
            //Probably 200 here so continue from here

//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getContext(), "Building Added Successfully!", Toast.LENGTH_SHORT).show();
//                    goBackToBuildingList();
//                }
//            });
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void goBackToBuildingList() {
        Log.d(TAG, "refreshUI: ");
        NavHostFragment.findNavController(CreateBuilding.this).navigate(R.id.action_createBuilding_to_buildingList);
    }

    private void findViews(View view) {
        building_LBL_name = view.findViewById(R.id.building_LBL_name);
        building_LBL_address = view.findViewById(R.id.building_LBL_address);
        building_LBL_floors = view.findViewById(R.id.building_LBL_floors);
        building_LBL_apartments = view.findViewById(R.id.building_LBL_apartments);
        building_BTN_createBuilding = view.findViewById(R.id.building_BTN_createBuilding);
    }
}