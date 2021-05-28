package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Facility;

public class CreateFacility extends Fragment {

    protected View view;
    private Spinner facility_Spinner_type;
    private Spinner facility_Spinner_ready;
    private MaterialButton facility_BTN_createFacility;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null)// Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_create_facility, container, false);

        //find all views
        findViews();

        //listener. create new facility
        facility_BTN_createFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Facility facility = new Facility();
                facility.setType(facility_Spinner_type.getSelectedItem().toString());
                facility.setReady(Boolean.parseBoolean(facility_Spinner_ready.getSelectedItem().toString()));
                postFacilityToDataBase(facility);
            }
        });

        return view;
    }

    // TODO : once the api is ready
    private void postFacilityToDataBase(Facility facility) {

    }

    private void findViews() {
        facility_Spinner_type = view.findViewById(R.id.facility_Spinner_type);
        facility_Spinner_ready = view.findViewById(R.id.facility_Spinner_ready);
        facility_BTN_createFacility = view.findViewById(R.id.facility_BTN_createFacility);

    }
}