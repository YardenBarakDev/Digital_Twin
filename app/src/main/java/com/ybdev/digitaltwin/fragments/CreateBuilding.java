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
import com.ybdev.digitaltwin.items.objects.Facility;
import com.ybdev.digitaltwin.util.MySP;

import java.io.IOException;

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
    private MaterialButton    building_BTN_createBuilding;
    private String projectID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.create_building, container, false);
        }

        projectID = MySP.getInstance().getString(MySP.KEYS.PROJECT, "");
        findViews(view);

        building_BTN_createBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building building = new Building();
                try {

                    building.setFloor(Integer.parseInt(building_LBL_floors.getText().toString()));
                    building.setID(building_LBL_name.getText().toString());
                    building.setNumOfWorkers(Integer.parseInt(building_LBL_apartments.getText().toString()));
                    building.setName(building_LBL_name.getText().toString());
                    building.setFacilities(null);
                    building.setReady(true);
                    building.setApartments(null);


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
        new Thread(new Runnable() {
            @Override
            public void run() {
                postInfoToDb(building);
            }
        }).start();
    }

    private void postInfoToDb(Building building) {
        String url = "http://192.168.1.202:8042/twins/items/2021b.vadim.kandorov/dima@notfound.com";

        OkHttpClient okHttpClient = new OkHttpClient();

        Gson gson = new Gson();

        String details = gson.toJson(building);

        Log.d(TAG, "postInfoToDb: " + details);

        String json = "{\n" +
                "    \"type\": \"Building\",\n" +
                "    \"name\": \"demo item " + building.getID() + " \",\n" +
                "    \"active\": true,\n" +
                "    \"createdTimestamp\": \"2021-05-20T10:42:23.995+00:00\",\n" +
                "    \"createdBy\": {\n" +
                "        \"userId\": {\n" +
                "            \"space\": \"2021b.vadim.kandorov\",\n" +
                "            \"email\": \"dima@notfound.com\"\n" +
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
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void findViews(View view) {

        building_LBL_name = view.findViewById(R.id.building_LBL_name);
        building_LBL_address = view.findViewById(R.id.building_LBL_address);
        building_LBL_floors = view.findViewById(R.id.building_LBL_floors);
        building_LBL_apartments = view.findViewById(R.id.building_LBL_apartments);
        building_BTN_createBuilding = view.findViewById(R.id.building_BTN_createBuilding);


    }
}