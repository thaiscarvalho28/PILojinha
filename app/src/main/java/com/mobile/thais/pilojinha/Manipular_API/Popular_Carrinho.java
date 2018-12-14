package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mobile.thais.pilojinha.Model.ItemCarrinho;
import com.mobile.thais.pilojinha.View.ActivLoja;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Popular_Carrinho extends AsyncTask<String, Void, ItemCarrinho> {
    int statusCode = 0;
    ActivLoja at_loja = new ActivLoja();
    List<ItemCarrinho> listProduto_carrinho = new ArrayList<>();
    private String URL_API = URLconfig.URL_API;
    private Context context;


    public void request(String url, String method, JSONObject json, String token, Context context) {
        this.execute(url, method, json.toString(), token);
        this.context = context;
    }

    @Override
    protected ItemCarrinho doInBackground(String... param) {

        HttpURLConnection httpURLConnection = null;

        try {

            String res_body = null;
            StringBuffer response = new StringBuffer();


            URL url = new URL(URL_API + param[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(param[1]);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Authorization", param[3]);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);


            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(param[2]);
            wr.flush();
            wr.close();

            statusCode = httpURLConnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            res_body = response.toString();

            Gson gson = new Gson();
            ItemCarrinho item_add_carrinho = gson.fromJson(res_body, ItemCarrinho.class);


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
    protected void onPostExecute(ItemCarrinho code) {

    }
}
