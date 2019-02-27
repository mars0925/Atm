package com.mars.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mars.atm.adapter.TransationAdapter;
import com.mars.atm.dao.TranstTask;
import com.mars.atm.model.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrasnsActivity extends AppCompatActivity {
    private static final String TAG = TrasnsActivity.class.getSimpleName();
    private ArrayList<Transaction> transactionList;
    private RecyclerView r_transation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasns);
        r_transation = findViewById(R.id.r_transation);
        r_transation.setLayoutManager(new LinearLayoutManager(this));
        r_transation.setHasFixedSize(true);

//        new TranstTask(this).execute("http://atm201605.appspot.com/h");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://atm201605.appspot.com/h").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                Log.e(TAG, "onResponse: "+ data );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseJson(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void parseJson(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        transactionList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String acount = jsonObject.getString("account");
            String date = jsonObject.getString("date");
            int amount = jsonObject.getInt("amount");
            int type = jsonObject.getInt("type");

            transactionList.add(new Transaction(date,acount,amount,type));
        }

        r_transation.setAdapter(new TransationAdapter(transactionList));
    }
}
