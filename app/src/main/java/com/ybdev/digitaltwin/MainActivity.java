package com.ybdev.digitaltwin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ybdev.digitaltwin.items.objects.Room;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "jjjj";
    private TextView SignIn_LBL_createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignIn_LBL_createAccount = findViewById(R.id.SignIn_LBL_createAccount);
        new Thread(new Runnable() {
            @Override
            public void run() {
                postInfoToDb();
                getInfoFromServer();
            }
        }).start();



    }

    private void postInfoToDb(){
        String url = "http://192.168.1.202:8042/twins/items/abc/def";

        OkHttpClient okHttpClient = new OkHttpClient();


        String json = "{\n" +
                "    \"type\": \"demoType\",\n" +
                "    \"name\": \"demo item\",\n" +
                "    \"active\": true,\n" +
                "    \"createdTimestamp\": \"2021-05-20T10:42:23.995+00:00\",\n" +
                "    \"createdBy\": {\n" +
                "        \"userId\": {\n" +
                "            \"space\": \"abc\",\n" +
                "            \"email\": \"def\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"location\": null,\n" +
                "    \"itemAttributes\": {\n" +
                "        \"ID\": \"770\",\n" +
                "        \"length\": 3.0,\n" +
                "        \"width\": 3.0,\n" +
                "        \"height\": 122.0,\n" +
                "        \"numOfWindows\": 1,\n" +
                "        \"airConditioner\": true,\n" +
                "        \"isRoomReady\": true,\n" +
                "        \"type\": null\n" +
                "    },\n" +
                "    \"itemId\": {\n" +
                "        \"space\": \"abc\",\n" +
                "        \"id\": \"1\"\n" +
                "    }\n" +
                "}";

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

    private void getInfoFromServer() {
        String url = "http://192.168.1.202:8042/twins/items/abc/def";

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
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONObject itemAttributes = (JSONObject) jsonObject.get("itemAttributes");
                    Room room = gson.fromJson(String.valueOf(itemAttributes), Room.class);
                    Log.d(TAG, "Object ="+room.toString());
                    Log.d(TAG, "onResponse:" + itemAttributes.toString() );
                } catch (JSONException e) {
                    Log.d(TAG, "onResponse: faild "+e.getMessage());
                    e.printStackTrace();
                }

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