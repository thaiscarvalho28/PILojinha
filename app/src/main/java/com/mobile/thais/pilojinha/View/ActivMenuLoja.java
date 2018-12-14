package com.mobile.thais.pilojinha.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mobile.thais.pilojinha.R;

public class ActivMenuLoja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_loja);
    }

    public void btnPerfil(View view) {
        Intent intent = new Intent(this, ActivPerfilUsuario.class);
        startActivity(intent);
    }

    public void btnCarrinho(View view) {
        Intent intent = new Intent(this, ActivCarrinho.class);
        startActivity(intent);
    }

    public void btnLoja(View view) {
        Intent intent = new Intent(this, ActivLoja.class);
        startActivity(intent);
    }
}
