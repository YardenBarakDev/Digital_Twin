package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Room;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CreateApartment extends Fragment {
    private static final String TAG = "CreateApartment";
    protected View view;
    private TextInputEditText Apartment_LBL_name;
    private TextInputEditText Apartment_LBL_balcony;
    private TextInputEditText Apartment_LBL_showers;
    private TextInputEditText Apartment_LBL_rooms;
    private MaterialButton Apartment_BTN_createApartment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_create_apartment, container, false);

        //find all views
        findViews();

        // listener. create new room
        Apartment_BTN_createApartment.setOnClickListener(view -> {
            Apartment apartment = new Apartment();
            // TODO : continue apartment build according to input from the api
            createApartment();
        });


        return view;
    }


    // TODO : once the api is ready
    private void createApartment() {
        new Thread(new Runnable() {
            @Override
            public void run() {

        for (int i = 0; i < 10; i++) {
            Apartment apartment = new Apartment("iD "+i, false, 0.0 + i%3, 0.0 + i%3, 0.0 + i%3,i,
                    10000.0 + i, 2,2, true, true,
                    Apartment.apartmentOrientation.EAST, true, false, false);
                    postAppartement(apartment);
        }

            }
        }).start();
    }

    private void postAppartement(Apartment apartment) {
        postInfoToDb(apartment);
    }

    private void postInfoToDb(Apartment apartment){
        String url = "http://192.168.1.202:8042/twins/items/2021b.vadim.kandorov/dima@notfound.com";

        OkHttpClient okHttpClient = new OkHttpClient();

        Gson gson = new Gson();

        String details = gson.toJson(apartment);

        Log.d(TAG, "postInfoToDb: "+details);

        String json = "{\n" +
                "    \"type\": \"Apartment\",\n" +
                "    \"name\": \"demo item "+apartment.getID()+" \",\n" +
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
                "    \"itemAttributes\":"+details+",\n" +
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
            Log.d(TAG, "postInfoToDb: "+response.code());
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: "+e.getMessage());
            e.printStackTrace();
        }
    }


    private void findViews() {
        Apartment_LBL_name = view.findViewById(R.id.Apartment_LBL_name);
        Apartment_LBL_balcony = view.findViewById(R.id.Apartment_LBL_balcony);
        Apartment_LBL_showers = view.findViewById(R.id.Apartment_LBL_showers);
        Apartment_LBL_rooms = view.findViewById(R.id.Apartment_LBL_rooms);
        Apartment_BTN_createApartment = view.findViewById(R.id.Apartment_BTN_createApartment);
    }
}