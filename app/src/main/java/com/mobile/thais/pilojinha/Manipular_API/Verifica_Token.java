package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Intent;
import android.os.AsyncTask;

import com.mobile.thais.pilojinha.View.MainActivity;
import com.mobile.thais.pilojinha.View.MenuLoja;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Verifica_Token extends AsyncTask<String, Void, Integer> {

    private String URL_API = URLconfig.URL_API;

    public void request(String url, String method, String token) {
        this.execute(url, method, token);
    }


    @Override
    protected Integer doInBackground(String... params) {

        int statusCode = 0;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(URL_API + params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(params[1]);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Authorization", params[2]);
            httpURLConnection.setDoInput(true);

            statusCode = httpURLConnection.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return statusCode;
    }


    @Override
    protected void onPostExecute(Integer code) {

        if (code.equals(200)) {
            Intent intent = new Intent(MainActivity.context, MenuLoja.class);
            MainActivity.context.startActivity(intent);
        } else {
            return;
        }
    }
}


