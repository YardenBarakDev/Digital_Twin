package com.ybdev.digitaltwin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.fragments.ProjectList;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;
import com.ybdev.digitaltwin.util.MySP;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private static final String TAG = "AdapterBuilding";
    private Context context;
    private ArrayList<ConstructionProject> items;
    private Fragment fragment;

    public ProjectAdapter(Context context , ArrayList<ConstructionProject> items , Fragment fragment) {
        Log.d(TAG, "RecyclerViewAdapterBuilding: ");
        this.context = context;
        this.items = items;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_project, parent, false);
        return  new ProjectAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        ConstructionProject temps = items.get(position);
        if(holder != null){
            holder.project_LBL_name.setText("Name : "+temps.getName());
            holder.project_LBL_name1.setText("Budget : "+temps.getBudget());
            holder.project_LBL_name2.setText("Start :" + temps.getStartDate());
            holder.project_LBL_name3.setText("End : "+ temps.getDueDate());
            holder.project_LBL_name4.setText("Loctation : "+ temps.getLat() +" , " + temps.getLon());
            holder.project_LAY_crd.setOnClickListener(view -> {
                MySP.getInstance().putString(MySP.KEYS.PROJECT,temps.getId());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_projectList_to_buildingList);
            });
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView project_LAY_crd;
        private MaterialTextView project_LBL_name;
        private MaterialTextView project_LBL_name1;
        private MaterialTextView project_LBL_name2;
        private MaterialTextView project_LBL_name3;
        private MaterialTextView project_LBL_name4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);

        }

        private void findViews(View view) {
            project_LAY_crd = view.findViewById(R.id.project_LAY_crd);
            project_LBL_name = view.findViewById(R.id.project_LBL_name);
            project_LBL_name1 = view.findViewById(R.id.project_LBL_name1);
            project_LBL_name2 = view.findViewById(R.id.project_LBL_name2);
            project_LBL_name3 = view.findViewById(R.id.project_LBL_name3);
            project_LBL_name4 = view.findViewById(R.id.project_LBL_name4);

        }
    }


}
