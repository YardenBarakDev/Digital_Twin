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
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;


public class CreateProject extends Fragment {
    private static final String TAG = "CreateProject";
    protected View view;
    private TextInputEditText Project_LBL_name;
    private TextInputEditText Project_LBL_budget;
    private TextInputEditText Project_LBL_startDate;
    private TextInputEditText Project_LBL_endDate;
    private TextInputEditText Project_LBL_location;
    private MaterialButton    Project_BTN_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.create_project, container, false);
        }
        findViews(view);

        ConstructionProject c = new ConstructionProject();


        Project_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.setBudget(Double.parseDouble(Project_LBL_budget.getText().toString()));
                    c.setStartDate(Project_LBL_startDate.getText().toString());
                    c.setDueDate(Project_LBL_endDate.getText().toString());
                    String[] loc = Project_LBL_location.getText().toString().split(",");
                    c.setLon(Double.parseDouble(loc[0]));
                    c.setLat(Double.parseDouble(loc[1]));
                    c.setName(Project_LBL_name.getText().toString());

                    postToDataBase(c); // TODO : add method post to server


                    NavHostFragment.findNavController(CreateProject.this).navigate(R.id.action_createProject_to_projectList);
                }catch (Exception e){
                    Log.d(TAG, "onClick: "+ e.getMessage());
                    Toast.makeText(getContext(), "Please enter correct porject", Toast.LENGTH_SHORT).show();
                }

                Log.d("CreateProject", "onClick: "+c.toString());
            }
        });

        return view;
    }

    private void postToDataBase(ConstructionProject c) {
    }


    private void findViews(View view) {
        Project_LBL_name = view.findViewById(R.id.Project_LBL_name);
        Project_LBL_budget = view.findViewById(R.id.Project_LBL_budget);
        Project_LBL_startDate = view.findViewById(R.id.Project_LBL_startDate);
        Project_LBL_endDate = view.findViewById(R.id.Project_LBL_endDate);
        Project_LBL_location = view.findViewById(R.id.Project_LBL_location);
        Project_BTN_register = view.findViewById(R.id.Project_BTN_register);

    }
}