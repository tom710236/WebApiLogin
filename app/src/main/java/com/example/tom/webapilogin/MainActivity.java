package com.example.tom.webapilogin;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    TextView test;
    EditText eId,ePw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request.Builder()
                .url("http://demo.shinda.com.tw/ModernWebApi/WebApiLogin.aspx")
                .build();
        Call call = client.newCall(request);
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
                    Log.d("JSOM",cMsg+"/"+cStatus+"/"+cUserID+"/"+cUserName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void Login(View v) {

        String params = "uId&uPw";
        URL url = null;
        try {
            url = new URL("http://demo.shinda.com.tw/ModernWebApi/WebApiLogin.aspx");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setDoOutput(true);
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(
                    conn.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.write(params);
        } catch (IOException e) {
            e.printStackTrace();
        }


        eId =(EditText)findViewById(R.id.eID);
        ePw =(EditText)findViewById(R.id.ePW);
        String uId = eId.getText().toString();
        String uPw = ePw.getText().toString();
        if(uId.length()==0||uPw.length()==0){
            new AlertDialog.Builder(this).setMessage("請輸入帳號或密碼")
                    .setTitle("提醒")
                    .setPositiveButton("ok",null)
                    .show();
        }else{
            if(uId.equals("carlos")&&uPw.equals("123")){
                Toast.makeText(this,"登入成功",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"登入失敗 請重新輸入",Toast.LENGTH_LONG).show();
            }

        }


    }

}
