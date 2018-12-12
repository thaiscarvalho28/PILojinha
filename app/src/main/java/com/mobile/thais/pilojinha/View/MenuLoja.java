package com.mobile.thais.pilojinha.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mobile.thais.pilojinha.R;

public class MenuLoja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_loja);
    }

    public void btnPerfil(View view) {
        Intent intent = new Intent(this, PerfilUsuario.class);
        startActivity(intent);
    }

    public void btnCarrinho(View view) {
        Intent intent = new Intent(this, Carrinho.class);
        startActivity(intent);
    }

    public void btnCategMasculino(View view) {
        Intent intent = new Intent(this, CategoriaMasculino.class);
        startActivity(intent);
    }

    public void btnCategFeminino(View view) {
        Intent intent = new Intent(this, CategoriaFeminino.class);
        startActivity(intent);
    }

    public void btnCategInfaMasc(View view) {
        Intent intent = new Intent(this, CategoriaInfantilMasc.class);
        startActivity(intent);
    }

    public void btnCategIntanFeme(View view) {
        Intent intent = new Intent(this, CategoriaInfantilFeme.class);
        startActivity(intent);
    }
}
