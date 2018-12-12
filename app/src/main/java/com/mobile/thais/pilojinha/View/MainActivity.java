package com.mobile.thais.pilojinha.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.thais.pilojinha.Manipular_API.Manipular_API_Login;
import com.mobile.thais.pilojinha.Manipular_API.Verifica_Token;
import com.mobile.thais.pilojinha.Model.Session;
import com.mobile.thais.pilojinha.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText EmailLogin;
    EditText SenhaLogin;
    TextView ErrorLogin;

    public static Context context;
    public static Activity fa;

    public String email;
    public String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verificar_autenticacao();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailLogin = findViewById(R.id.textEmailLogin);
        SenhaLogin = findViewById(R.id.textSenhaLogin);
        ErrorLogin = findViewById(R.id.viewErroLogin);
    }

    private void verificar_autenticacao() {
        Verifica_Token verifica = new Verifica_Token();
        Session session = new Session(this);

        verifica.request("autenticar/carrinho", "GET", session.token());
    }

    public void btnLogar(View view) {
        email = String.valueOf(EmailLogin.getText().toString());
        senha = String.valueOf(SenhaLogin.getText().toString());

        JSONObject cliJson = new JSONObject();
        Manipular_API_Login request = new Manipular_API_Login();

        try {

            cliJson.put("senha", senha);
            cliJson.put("email", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.request("cliente/login", "POST", cliJson);

    }

    public void btnCriarConta(View view) {
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);
    }
}
