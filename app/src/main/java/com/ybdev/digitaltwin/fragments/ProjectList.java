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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.ybdev.digitaltwin.Adapter.ProjectAdapter;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;

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


public class ProjectList extends Fragment {
    private static final String TAG = "ProjectList";
    protected View view ;
    private FloatingActionButton project_FAB_create_new_building;
    private RecyclerView project_RecyclerView;
    private ArrayList<ConstructionProject> allProject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.project_lists, container, false);
        }
        findViews(view);

        allProject = new ArrayList<>();
        getInfoFromServer();

        project_FAB_create_new_building.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_projectList_to_createProject));

        return view;
    }

    private void setAdapter() {
        ProjectAdapter projectAdapter = new ProjectAdapter(getContext() , allProject , this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        project_RecyclerView.setLayoutManager(linearLayoutManager);
        project_RecyclerView.setAdapter(projectAdapter);
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
                        if(itemAttributes.equals("ConstructionProject")){
                            JSONObject itemobj = (JSONObject) jsonObject.get("itemAttributes");
                            ConstructionProject constructionProject = gson.fromJson(String.valueOf(itemobj), ConstructionProject.class);
                            allProject.add(constructionProject);
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
                        Log.d(TAG, "run: "+allProject.toString());
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

    private void findViews(View view) {
        project_FAB_create_new_building = view.findViewById(R.id.project_FAB_create_new_building);
        project_RecyclerView = view.findViewById(R.id.project_RecyclerView);
    }
}