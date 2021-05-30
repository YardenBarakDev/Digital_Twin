package com.ybdev.digitaltwin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.items.objects.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends Fragment {
    private static final String TAG = "Register";
    private View view;
    private TextInputEditText Register_LBL_username;
    private TextInputEditText Register_LBL_email;
    private AutoCompleteTextView Register_LBL_role;
    private TextInputEditText Register_LBL_password;
    private MaterialButton    Register_BTN_register;

    private static final String[] ROLES = new String[] {
            "PLAYER", "MANAGER", "ADMIN"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.register, container, false);
        }
        findViews(view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, ROLES);
        Register_LBL_role.setAdapter(adapter);


        Register_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    User user = new User();
                    user.setUserName(Register_LBL_username.getText().toString());
                    user.setEmail(Register_LBL_email.getText().toString());
                    user.setPassword(Register_LBL_password.getText().toString());
                    user.setRole(Register_LBL_role.getText().toString());

                    postUser(user); // TODO : add method to post user

                    NavHostFragment.findNavController(Register.this).navigate(R.id.action_register_to_projectList);

                }catch (Exception e){
                    Log.d(TAG, "onClick: "+ e.getMessage());
                    Toast.makeText(getContext(), "Please enter correct building", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void postUser(User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postInfoToDb(user);
            }
        }).start();
    }

    private void postInfoToDb(User user){
        String url = "http://192.168.1.202:8042/twins/users";

        OkHttpClient okHttpClient = new OkHttpClient();


        String json = "{" +
            "\"email\": " +"\""+ user.getEmail()+ "\" , "+
            "\"role\":" + "\""+user.getRole().toUpperCase()+"\" , "+
            "\"username\":" + "\""+user.getUserName()+"\" , "+
            "\"avatar\":" +"\""+ ""+"\""+
                "}" ;

        Log.d(TAG, "postInfoToDb: "+json);

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

    private void findViews(View view) {
        Register_LBL_username = view.findViewById(R.id.Register_LBL_username);
        Register_LBL_email = view.findViewById(R.id.Register_LBL_email);
        Register_LBL_role = view.findViewById(R.id.Register_LBL_roles);
        Register_LBL_password = view.findViewById(R.id.Register_LBL_password);
        Register_BTN_register = view.findViewById(R.id.Register_BTN_register);

    }
}