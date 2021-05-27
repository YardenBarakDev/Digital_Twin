package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ybdev.digitaltwin.Adapter.ProjectAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;

import java.util.ArrayList;


public class ProjectList extends Fragment {

protected View view ;

private FloatingActionButton project_FAB_create_new_building;
private RecyclerView project_RecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.project_lists, container, false);
        }
        findViews(view);

        project_FAB_create_new_building.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_projectList_to_createProject));

        ArrayList<ConstructionProject> allProject = new ArrayList<>();
        getProjectData(allProject); // TODO : add method get all project

        ProjectAdapter projectAdapter = new ProjectAdapter(getContext() , allProject , this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        project_RecyclerView.setLayoutManager(linearLayoutManager);
        project_RecyclerView.setAdapter(projectAdapter);

        return view;
    }

    private void getProjectData(ArrayList<ConstructionProject> allProject) {
        allProject.add(new ConstructionProject("0",3.12,3.16, 30000000.0, "12/12/2021", "12/12/2022",
                null));

        allProject.add(new ConstructionProject("0",3.12,3.16, 30000000.0, "12/12/2021", "12/12/2022",
                null));
    }

    private void findViews(View view) {
        project_FAB_create_new_building = view.findViewById(R.id.project_FAB_create_new_building);
        project_RecyclerView = view.findViewById(R.id.project_RecyclerView);
    }
}