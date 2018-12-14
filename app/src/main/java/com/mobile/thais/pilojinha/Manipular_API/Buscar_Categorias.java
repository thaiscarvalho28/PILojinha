package com.mobile.thais.pilojinha.Manipular_API;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mobile.thais.pilojinha.Model.Categoria;
import com.mobile.thais.pilojinha.View.ActivLoja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Buscar_Categorias extends AsyncTask<String, Void, String> {

    private String URL_API = URLconfig.URL_API;
    int statusCode = 0;

    ActivLoja activLoja = new ActivLoja();
    List<Categoria> listaCategorias = new ArrayList<>();


    public void request(String url, String method) {
        this.execute(url, method);
    }


    @Override
    protected String doInBackground(String... param) {

        String res_body = null;
        HttpURLConnection httpURLConnection = null;
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(URL_API + param[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(param[1]);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoInput(true);

            statusCode = httpURLConnection.getResponseCode(); //statusCode = 400

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            res_body = response.toString();

            Gson gson = new Gson();
            Categoria[] cat_array = gson.fromJson(res_body, Categoria[].class);

            for (int i = 0; i < cat_array.length; i++) {
                listaCategorias.add(cat_array[i]);
            }


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

        return null;

    }

    @Override
    protected void onPostExecute(String categorias) {

        activLoja.listCategoria.addAll(listaCategorias);
        activLoja.adapter.notifyDataSetChanged();
    }

}
