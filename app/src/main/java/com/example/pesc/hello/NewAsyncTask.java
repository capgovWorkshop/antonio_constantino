package com.example.pesc.hello;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by PESC on 29/05/2015.
 */
public class NewAsyncTask extends AsyncTask<URL, String, String> {

    Context context;
    OnPostExecuteListener onPostExecuteListener;

    public static interface OnPostExecuteListener{
        void onPostExecute(String result);
    }

    public NewAsyncTask() {
    }

    public NewAsyncTask(Context context, OnPostExecuteListener onPostExecuteListener) {
        this.context = context;
        this.onPostExecuteListener = onPostExecuteListener;
        if (onPostExecuteListener == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(URL... url) {

       // url = null;



        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url[0].openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

// read the response
        try {
            System.out.println("Response Code: " + conn.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(conn.getInputStream());
            return IOUtils.toString(in, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        if (onPostExecuteListener != null){
            try {
                onPostExecuteListener.onPostExecute(result);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
