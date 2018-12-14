package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobile.thais.pilojinha.Model.Carrinho;
import com.mobile.thais.pilojinha.Model.ItemCarrinho;
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

public class Buscar_Carrinho extends AsyncTask<String, Void, String> {

    private String URL_API = URLconfig.URL_API;
    int statusCode = 0;
    double valor_carrinho = 0;
    ActivLoja activLoja = new ActivLoja();
    List<ItemCarrinho> listItemCarrinho = new ArrayList<>();

    Context context;
    TextView textView;

    public Buscar_Carrinho(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    public void request(String url, String method, String token) {
        this.execute(url, method, token);
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
            httpURLConnection.setRequestProperty("Authorization", param[2]);
            httpURLConnection.setDoInput(true);

            statusCode = httpURLConnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            res_body = response.toString();

            Gson gson = new Gson();
            Carrinho cat_array = gson.fromJson(res_body, Carrinho.class);

            listItemCarrinho = cat_array.getItens();

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
    protected void onPostExecute(String carrinho) {
        activLoja.listItemCarrinho.clear();
        activLoja.listItemCarrinho.addAll(listItemCarrinho);

        if (listItemCarrinho.size() > 0) {
            for (int i = 0; i < listItemCarrinho.size(); i++) {
                double quantidade = listItemCarrinho.get(i).getQuantidade();
                double preco = listItemCarrinho.get(i).getProduto().getPreco();
                valor_carrinho = valor_carrinho + (quantidade * preco);
            }
            String valor = String.valueOf(valor_carrinho);
            textView.setText(valor);
        }
    }
}
