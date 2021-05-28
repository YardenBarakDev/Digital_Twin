package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;


public class CreateApartment extends Fragment {

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
    }

    private void findViews() {
        Apartment_LBL_name = view.findViewById(R.id.Apartment_LBL_name);
        Apartment_LBL_balcony = view.findViewById(R.id.Apartment_LBL_balcony);
        Apartment_LBL_showers = view.findViewById(R.id.Apartment_LBL_showers);
        Apartment_LBL_rooms = view.findViewById(R.id.Apartment_LBL_rooms);
        Apartment_BTN_createApartment = view.findViewById(R.id.Apartment_BTN_createApartment);
    }
}