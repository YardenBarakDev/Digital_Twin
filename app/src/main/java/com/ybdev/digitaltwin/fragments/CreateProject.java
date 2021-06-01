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
import com.google.gson.Gson;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.Building;
import com.ybdev.digitaltwin.items.objects.ConstructionProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


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
                    c.setId(UUID.randomUUID().toString());
                    c.setBuildings(new ArrayList<Building>());
                    postToDataBase(c); // TODO : add method post to server
                    Toast.makeText(getContext(), "project created successfully", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(CreateProject.this).navigate(R.id.action_createProject_to_projectList);
                }catch (Exception e){
                    Log.d(TAG, "onClick: "+ e.getMessage());
                    Toast.makeText(getContext(), "Please enter correct project", Toast.LENGTH_SHORT).show();
                }

                Log.d("CreateProject", "onClick: "+c.toString());
            }
        });

        return view;
    }

    private void postToDataBase(ConstructionProject c) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postInfoToDb(c);
            }
        }).start();
    }

    private void postInfoToDb(ConstructionProject c) {
        String url = "http://192.168.1.202:8042/twins/items/2021b.vadim.kandorov/dima@notfound.com";

        OkHttpClient okHttpClient = new OkHttpClient();

        Gson gson = new Gson();

        String details = gson.toJson(c);

        Log.d(TAG, "postInfoToDb: " + details);

        String json = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            json = "{\n" +
                    "    \"type\": \"ConstructionProject\",\n" +
                    "    \"name\": \"demo item " + c.getId() + " \",\n" +
                    "    \"active\": true,\n" +
                    "    \"createdTimestamp\": \""+java.time.LocalDateTime.now() +" \",\n" +
                    "    \"createdBy\": {\n" +
                    "        \"userId\": {\n" +
                    "            \"space\": \"2021b.vadim.kandorov\",\n" +
                    "            \"email\": \"dima@notfound.com\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"location\": {\n" +
                    "\"lat\":"+c.getLat()+",\n" +
                    "\"lng\":"+c.getLon()+"\n" +
                    "},\n" +
                    "    \"itemAttributes\":" + details + ",\n" +
                    "    \"itemId\": {\n" +
                    "        \"space\": \"2021b.vadim.kandorov\",\n" +
                    "        \"id\": \"1\"\n" +
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
        } catch (IOException e) {
            Log.d(TAG, "postInfoToDb: " + e.getMessage());
            e.printStackTrace();
        }
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