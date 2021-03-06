package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.util.MySP;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CreateApartment extends Fragment {
    private static final String TAG = "CreateApartment";
    protected View view;
    private TextInputEditText Apartment_LBL_name;
    private TextInputEditText Apartment_LBL_price;
    private TextInputEditText Apartment_LBL_oriantation;
    private TextInputEditText Apartment_LBL_rooms;
    private MaterialButton Apartment_BTN_createApartment;
    private String userEmail = MySP.getInstance().getString(MySP.KEYS.USER_EMAIL, "");

    private String parentBuilding = MySP.getInstance().getString(MySP.KEYS.PARENT_BUILDING, "");

    private Building building;


    private TextView noApartments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_create_apartment, container, false);

        //find all views
        findViews();

        String projectString = MySP.getInstance().getString(MySP.KEYS.PROJECT, "");
        //project = new Gson().fromJson(projectString, ConstructionProject.class);


        // listener. create new room
        Apartment_BTN_createApartment.setOnClickListener(view -> {
            Apartment apartment = new Apartment(Apartment_LBL_name.getText().toString(), parentBuilding, false, 0.0 + Math.random() * 3 + 1 % 3, 0.0 + Math.random() * 3 + 1 % 3, 0.0 + Math.random() * 3 + 1 % 3,
                    Integer.parseInt(Apartment_LBL_rooms.getText().toString()), Double.parseDouble(Apartment_LBL_price.getText().toString()), 2, 2, true, true,
                    Apartment.apartmentOrientation.EAST, true, false, false);

            createApartment(apartment);
        });


        return view;
    }


    // TODO : once the api is ready
    private void createApartment(Apartment apartment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postAppartement(apartment);
            }
        }).start();
    }

    private void postAppartement(Apartment apartment) {
        addApartmentToDB(apartment);
    }

    private void addApartmentToDB(Apartment apartment) {
        String url = "http://192.168.43.243:8042/twins/items/2021b.vadim.kandorov/" + userEmail;

        OkHttpClient okHttpClient = new OkHttpClient();

        Gson gson = new Gson();

        String details = gson.toJson(apartment);

        Log.d(TAG, "postInfoToDb: " + details);

        String json = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            json = "{\n" +
                    "    \"type\": \"Apartment\",\n" +
                    "    \"name\": \"Apartment " + apartment.getID() + " \",\n" +
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
                    "        \"id\": \"" + apartment.getID() + "\"\n" +
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
                    Toast.makeText(getContext(), "Apartment Added Successfully!", Toast.LENGTH_SHORT).show();
                    goBackToApartmentList();
                }
            });

        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void goBackToApartmentList() {
        Log.d(TAG, "goBackToApartmentList: ");
        NavHostFragment.findNavController(CreateApartment.this).navigate(R.id.action_createApartment_to_apartmentList);

    }


    private void findViews() {
        noApartments = view.findViewById(R.id.apartmentList_LBL_emptyList);
        Apartment_LBL_name = view.findViewById(R.id.Apartment_LBL_name);
        Apartment_LBL_price = view.findViewById(R.id.Apartment_LBL_price);
        Apartment_LBL_oriantation = view.findViewById(R.id.Apartment_LBL_oriantation);
        Apartment_LBL_rooms = view.findViewById(R.id.Apartment_LBL_rooms);
        Apartment_BTN_createApartment = view.findViewById(R.id.Apartment_BTN_createApartment);
    }
}