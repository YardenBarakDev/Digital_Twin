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
import com.ybdev.digitaltwin.Adapter.ApartmentAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Room;

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

public class ApartmentList extends Fragment {
    private static final String TAG = "ApartmentList";
    protected View view;
    private Spinner Apartment_Spinner;
    private RecyclerView Apartment_RecyclerView;
    private FloatingActionButton Apartment_FAB_create_new_building;
    private ArrayList<Apartment> allApartments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null)// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_apartment_list, container, false);

        //init the array
        allApartments = new ArrayList<>();
        getAllApartment();
        //find all views
        findViews();

        // listener. once click will switch to create apartment fragment
        Apartment_FAB_create_new_building.setOnClickListener(view -> NavHostFragment.findNavController(ApartmentList.this).navigate(R.id.action_apartmentList_to_createApartment));

        setAdapter();
        return view;
    }

    private void getAllApartment() {
        getInfoFromServer();
    }

    private void getInfoFromServer() {
        String url = "http://192.168.1.202:8042/twins/items/2021b.vadim.kandorov/dima@notfound.com";

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
                        if(itemAttributes.equals("Apartment")){
                            JSONObject itemobj = (JSONObject) jsonObject.get("itemAttributes");
                            Apartment apartment = gson.fromJson(String.valueOf(itemobj), Apartment.class);
                            allApartments.add(apartment);
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
                        Log.d(TAG, "run: "+allApartments.toString());
                        setAdapter();
                    }
                });
                Log.d("jjjj", "onResponse: Got response: " + initialResponse);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("jjjj", "onFailure: Exception: " + e.getMessage());
            }

        });

    }

    // TODO : call this method when after the api call
    private void setAdapter() {

        ApartmentAdapter apartmentAdapter = new ApartmentAdapter(getContext() , allApartments);
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