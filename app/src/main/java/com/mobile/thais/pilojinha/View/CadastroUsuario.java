package com.mobile.thais.pilojinha.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.thais.pilojinha.Manipular_API.Manipular_API_CadastroUsuario;
import com.mobile.thais.pilojinha.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroUsuario extends AppCompatActivity{

    EditText textNome;
    EditText textEmail;
    EditText textSenha;
    Button btnCadastrar;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        this.context = getApplicationContext();

        textNome = findViewById(R.id.textNome);
        textEmail = findViewById(R.id.textEmail);
        textSenha = findViewById(R.id.textSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);


    }

    public void btnCadastrar(View v){
        JSONObject clienteJson = new JSONObject();
        Manipular_API_CadastroUsuario request = new Manipular_API_CadastroUsuario();

        try{

            clienteJson.put("email", textEmail.getText().toString());
            clienteJson.put("nome", textNome.getText().toString());
            clienteJson.put("senha", textSenha.getText().toString());

        } catch (JSONException e){
            e.printStackTrace();
        }

        request.request("cliente", "POST", clienteJson);

    }

}
