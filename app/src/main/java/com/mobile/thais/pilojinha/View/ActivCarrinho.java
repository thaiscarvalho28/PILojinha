package com.mobile.thais.pilojinha.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.thais.pilojinha.Manipular_API.Finalizar_Compras;
import com.mobile.thais.pilojinha.Manipular_API.Mostrar_Carrinho;
import com.mobile.thais.pilojinha.Model.ItemCarrinho;
import com.mobile.thais.pilojinha.Model.Session;
import com.mobile.thais.pilojinha.R;

import java.util.ArrayList;
import java.util.List;

public class ActivCarrinho extends AppCompatActivity implements View.OnClickListener{

    Mostrar_Carrinho mostrarCarrinho;
    Session session;
    TextView valor_total;
    public static ListView mListView;
    public static Context context;
    public static Activity actvCarrinho;
    Button btnFinalizarCompra;
    public static List<ItemCarrinho> listItemCarrinho = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        session = new Session(this);

        actvCarrinho = this;

        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);
        btnFinalizarCompra.setOnClickListener(this);

        mListView = findViewById(R.id.listv_carrinho);
        valor_total = findViewById(R.id.valor_compra);

        valor_total.setText("Valor Total: " + ActivLoja.txt_valor.getText());

        mostrarCarrinho = new Mostrar_Carrinho(this,valor_total);

        mostrarCarrinho.request("auth/carrinho", "GET", session.token());
    }

    @Override
    public void onClick(View v) {

        Finalizar_Compras finalizaCompras = new Finalizar_Compras();
        finalizaCompras.request("auth/compra", "POST", session.token());

    }
}
