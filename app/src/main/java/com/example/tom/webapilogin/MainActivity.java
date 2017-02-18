package com.example.tom.webapilogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request.Builder()
                .url("http://demo.shinda.com.tw/ModernWebApi/WebApiLogin.aspx")
                .build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d("OKHTTP", json);
                parseJson(json);
            }

            private void parseJson(String json) {

                try {
                    String cMsg = new JSONObject(json).getString("cMsg");
                    String cStatus = new JSONObject(json).getString("cStatus");
                    String cUserID = new JSONObject(json).getString("cUserID");
                    String cUserName = new JSONObject(json).getString("cUserName");
                    Log.d("JSOM",cMsg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

