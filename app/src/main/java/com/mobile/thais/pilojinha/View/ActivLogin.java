package com.mobile.thais.pilojinha.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.thais.pilojinha.Manipular_API.Manipular_API_Login;
import com.mobile.thais.pilojinha.Manipular_API.Verifica_Token;
import com.mobile.thais.pilojinha.Model.Session;
import com.mobile.thais.pilojinha.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivLogin extends AppCompatActivity {

    EditText EmailLogin;
    EditText SenhaLogin;
    Button btnLogar;
    Button btnCadastrar;

    public static Context context;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verificar_autenticacao();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = getApplicationContext();
        fa = this;

        EmailLogin = findViewById(R.id.textEmailLogin);
        SenhaLogin = findViewById(R.id.textSenhaLogin);
        btnLogar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    private void verificar_autenticacao() {
        Verifica_Token verifica = new Verifica_Token();
        Session session = new Session(this);

        verifica.request("autenticar/carrinho", "GET", session.token());
    }

    public void btnLogar(View view) {

        JSONObject clienteJson = new JSONObject();
        Manipular_API_Login request = new Manipular_API_Login();

        try {

            clienteJson.put("email", EmailLogin.getText().toString());
            clienteJson.put("senha", SenhaLogin.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.request("cliente/autenticar", "POST", clienteJson);

    }

    public void btnCriarConta(View view) {
        Intent intent = new Intent(this, ActivCadastroUsuario.class);
        startActivity(intent);
    }
}
