package com.ybdev.digitaltwin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView SignIn_LBL_createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignIn_LBL_createAccount = findViewById(R.id.SignIn_LBL_createAccount);
        getInfoFromServer();
    }

    private void getInfoFromServer() {
        String url = "https://official-joke-api.appspot.com/random_joke";

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String initialResponse = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SignIn_LBL_createAccount.setText(initialResponse);
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
}