package com.mars.atm.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static android.support.constraint.Constraints.TAG;

public class TranstTask extends AsyncTask<String, Void, String> {
    Context context;

    public TranstTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(TAG, "onPreExecute: " );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e(TAG, "onPostExecute: " + s);
    }

    /**
     * 不要做跟UI有關的事情
     * @param strings
     * @return
     */
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder sb = null;
        try {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            Log.e(TAG, "onCreate: " + sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }




}
