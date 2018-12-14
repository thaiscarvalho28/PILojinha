package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mobile.thais.pilojinha.View.ActivCarrinho;
import com.mobile.thais.pilojinha.View.ActivLogin;
import com.mobile.thais.pilojinha.View.ActivLoja;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Finalizar_Compras extends AsyncTask<String, Void, String> {
    private String URL_API = URLconfig.URL_API;
    int statusCode = 0;
    ActivCarrinho activCarrinho = new ActivCarrinho();

    public void request(String url, String method, String token) {
        this.execute(url, method, token);
    }

    @Override
    protected String doInBackground(String... param) {

        HttpURLConnection httpURLConnection = null;
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(URL_API + param[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(param[1]);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Authorization", param[2]);

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

        return null;
    }

    @Override
    protected void onPostExecute(String carrinho) {

        if (statusCode == 200){
            Toast.makeText(ActivLogin.context,"Compra realizada com sucesso!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(ActivLogin.context, ActivLoja.class);
            ActivLogin.context.startActivity(i);
        }else{
            Toast.makeText(ActivLogin.context,"Não foi possível finalizar a compra!",Toast.LENGTH_LONG).show();
        }

    }
}
