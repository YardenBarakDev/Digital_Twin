package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ybdev.digitaltwin.Adapter.ApartmentAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Apartment;
import java.util.ArrayList;

public class ApartmentList extends Fragment {

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

        //find all views
        findViews();

        // listener. once click will switch to create apartment fragment
        Apartment_FAB_create_new_building.setOnClickListener(view -> NavHostFragment.findNavController(ApartmentList.this).navigate(R.id.action_apartmentList_to_createApartment));

        return inflater.inflate(R.layout.fragment_apartment_list, container, false);
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