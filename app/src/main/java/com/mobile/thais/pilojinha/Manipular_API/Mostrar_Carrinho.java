package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobile.thais.pilojinha.Model.Carrinho;
import com.mobile.thais.pilojinha.Model.ItemCarrinho;
import com.mobile.thais.pilojinha.Model.Produto;
import com.mobile.thais.pilojinha.R;
import com.mobile.thais.pilojinha.View.ActivCarrinho;
import com.mobile.thais.pilojinha.View.ActivListaCarrinhoAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Mostrar_Carrinho extends AsyncTask<String, Void, String> {
    private String URL_API = URLconfig.URL_API;
    int statusCode = 0;
    ActivCarrinho activCarrinho = new ActivCarrinho();
    List<ItemCarrinho> listItemCarrinho = new ArrayList<>();
    List<Produto> listProdutos = new ArrayList<>();
    Context context;
    TextView textView;


    public Mostrar_Carrinho(Context context, TextView textView) {
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

        activCarrinho.listItemCarrinho.addAll(listItemCarrinho);

        for (int i = 0; i<listItemCarrinho.size(); i++){
            listProdutos.add(listItemCarrinho.get(i).getProduto());
        }

        ActivListaCarrinhoAdapter adapterCarrinho = new ActivListaCarrinhoAdapter(context, R.layout.activity_lista_carrinho_adapter, listItemCarrinho);
        activCarrinho.mListView.setAdapter(adapterCarrinho);

    }
}
