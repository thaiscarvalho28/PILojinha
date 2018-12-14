package com.mobile.thais.pilojinha.Manipular_API;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mobile.thais.pilojinha.View.ActivCadastroUsuario;
import com.mobile.thais.pilojinha.View.ActivLogin;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Manipular_API_CadastroUsuario extends AsyncTask<String, Void, Integer>{

    private String URL_API = URLconfig.URL_API;

    public void request(String URL, String metodo, JSONObject JSON){
        this.execute(URL, metodo, JSON.toString());
    }

    @Override
    protected Integer doInBackground(String... params) {

        int statusCode = 0;
        HttpURLConnection httpURLConnection = null;

        try{
            URL url = new URL(URL_API + params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(params[1]);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            DataOutputStream dataOut = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOut.writeBytes(params[2]);
            dataOut.flush();
            dataOut.close();

            statusCode = httpURLConnection.getResponseCode();


        } catch (MalformedURLException err){
            err.printStackTrace();
        } catch (ProtocolException err){
            err.printStackTrace();
        } catch (IOException err){
            err.printStackTrace();
        } finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
        }

        return statusCode;

    }

    @Override
    protected void onPostExecute(Integer code) {

        if(code.equals(201)){
            Toast.makeText(ActivCadastroUsuario.context,
                    "Usuário cadastrado com sucesso!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActivCadastroUsuario.context, ActivLogin.class);
            ActivCadastroUsuario.context.startActivity(intent);

        } else {

            Toast.makeText(ActivCadastroUsuario.context,
                    "Erro ao cadastrar. Tente novamente!",
                    Toast.LENGTH_LONG).show();
        }

    }

}
