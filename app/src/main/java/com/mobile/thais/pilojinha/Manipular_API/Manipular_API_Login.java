package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mobile.thais.pilojinha.Model.Session;
import com.mobile.thais.pilojinha.View.MainActivity;
import com.mobile.thais.pilojinha.View.MenuLoja;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Manipular_API_Login extends AsyncTask<String, Void, String> {

    private String URL_API = URLconfig.URL_API;
    int statusCode = 0;

    public void request(String URL, String metodo, JSONObject JSON){
        this.execute(URL, metodo, JSON.toString());
    }

    @Override
    protected String doInBackground(String... params) {
        String autenticacao = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(URL_API + params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(params[1]);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);


            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(params[2]);
            wr.flush();
            wr.close();

            statusCode = httpURLConnection.getResponseCode();
            autenticacao = httpURLConnection.getHeaderField("Authorization");


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

        return autenticacao;
    }

    @Override
    protected void onPostExecute(String token) {

        if (statusCode == 200) {

            Session session = new Session(MainActivity.context);
            session.setToken(token);

            Intent intent = new Intent(MainActivity.context, MenuLoja.class);
            MainActivity.context.startActivity(intent);

        } else {
            Toast.makeText(MainActivity.context,
                    "Erro ao logar! Verifique os dados e tente Novamente!",
                    Toast.LENGTH_LONG).show();
        }

    }
}
