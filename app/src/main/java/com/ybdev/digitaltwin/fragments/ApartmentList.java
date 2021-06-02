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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.ybdev.digitaltwin.Adapter.ApartmentAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.Room;
import com.ybdev.digitaltwin.util.MySP;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApartmentList extends Fragment {
    private static final String TAG = "ApartmentList";
    protected View view;
    private Spinner Apartment_Spinner;
    private RecyclerView Apartment_RecyclerView;
    private FloatingActionButton Apartment_FAB_create_new_building;
    private ArrayList<Apartment> allApartments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_apartment_list, container, false);

        //find all views
        findViews();

        //init the array
        allApartments = new ArrayList<>();
        String desiredId = MySP.getInstance().getString(MySP.KEYS.PARENT_BUILDING, "projectString");
        Log.d(TAG, "onCreateView: Parent building: " + desiredId);
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchApartmentsFromServer(desiredId);
            }
        }).start();

        // listener. once click will switch to create apartment fragment
        Apartment_FAB_create_new_building.setOnClickListener(view -> NavHostFragment.findNavController(ApartmentList.this).navigate(R.id.action_apartmentList_to_createApartment));

        return view;
    }


    private void fetchApartmentsFromServer(String buildingId) {
        Log.d(TAG, "fetchBuildingsFromServer: " + buildingId);

        String url = "http://192.168.43.243:8042/twins/operations";
        OkHttpClient okHttpClient = new OkHttpClient();

        String requestJson = "{\n" +
                "    \"operationId\": {\n" +
                "        \"space\": \"2021b.twins\",\n" +
                "        \"id\": \"451\"\n" +
                "    },\n" +
                "    \"type\": \"FETCH_APARTMENTS_BY_ID\",\n" +
                "    \"item\": {\n" +
                "        \"itemId\": {\n" +
                "            \"space\": \"2021b.vadim.kandorov\",\n" +
                "            \"id\": \"" + buildingId + "\"\n" +
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
            Log.d(TAG, "fetchApartmentsFromServer: " + responseBody);

            try {
                allApartments = new ArrayList<>();
                JSONArray apartmentsList = new JSONArray(responseBody);

                for (int i = 0; i < apartmentsList.length(); i++) {

                    JSONObject temp = (JSONObject) apartmentsList.get(i);
                    String attr = (String) temp.get("itemAttributes");
                    Log.d(TAG, "fetchBuildingsFromServer: " + attr);

                    Apartment tempApartment = new Gson().fromJson(attr, Apartment.class);
                    Log.d(TAG, "fetchBuildingsFromServer: Object = " + tempApartment.toString());

                    allApartments.add(tempApartment);
                    Log.d(TAG, "fetchBuildingsFromServer: ");
                }

                for (Apartment a : allApartments) {
                    Log.d(TAG, "fetchBuildingsFromServer: " + a.toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            //probably 200
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Apartments Fetched Successfully!", Toast.LENGTH_SHORT).show();
                    setAdapter(allApartments);
                }
            });
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setAdapter(ArrayList<Apartment> apartments) {

        ApartmentAdapter apartmentAdapter = new ApartmentAdapter(getContext(), allApartments,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Apartment_RecyclerView.setLayoutManager(linearLayoutManager);
        Apartment_RecyclerView.setAdapter(apartmentAdapter);
    }

    private void findViews() {
        Apartment_Spinner = view.findViewById(R.id.Apartment_Spinner);
        Apartment_RecyclerView = view.findViewById(R.id.Apartment_RecyclerView);
        Apartment_FAB_create_new_building = view.findViewById(R.id.Apartment_FAB_create_new_building);
    }
}