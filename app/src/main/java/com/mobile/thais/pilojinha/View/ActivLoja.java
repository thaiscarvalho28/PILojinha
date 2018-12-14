package com.mobile.thais.pilojinha.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.thais.pilojinha.Manipular_API.Buscar_Carrinho;
import com.mobile.thais.pilojinha.Manipular_API.Buscar_Categorias;
import com.mobile.thais.pilojinha.Manipular_API.Buscar_produtos;
import com.mobile.thais.pilojinha.Model.Categoria;
import com.mobile.thais.pilojinha.Model.ItemCarrinho;
import com.mobile.thais.pilojinha.Model.Produto;
import com.mobile.thais.pilojinha.Model.Session;
import com.mobile.thais.pilojinha.R;

import java.util.ArrayList;
import java.util.List;

public class ActivLoja extends AppCompatActivity implements View.OnClickListener{

    Spinner spinner;
    public static TextView txt_valor;

    Buscar_Carrinho buscaCarrinho;
    Session session;
    public static List<Categoria> listCategoria = new ArrayList<>();
    public static List<Produto> listProdutos = new ArrayList<>();
    public static ArrayAdapter<Categoria> adapter;
    public static List<ItemCarrinho> listItemCarrinho = new ArrayList<>();
    public static ListView mListView;
    public static Context context;
    ActivAlertDialog activAlert;
    Button btn_check_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        session = new Session(this);
        ActivLogin.fa.finish();
        if (ActivCarrinho.actvCarrinho != null){
            ActivCarrinho.actvCarrinho.finish();
        }

        btn_check_out = (Button) findViewById(R.id.btnCarrinho);
        btn_check_out.setOnClickListener(this);

        this.context = getApplicationContext();

        txt_valor = findViewById(R.id.viewValor);

        buscaCarrinho = new Buscar_Carrinho(this, txt_valor);

        mListView = findViewById(R.id.listaProdutos);
        spinner = findViewById(R.id.spinnerCategoria);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCategoria);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Categoria categoria = (Categoria) parent.getSelectedItem();
                displaySelectedCategory(categoria);

                Buscar_produtos buscarProdutos = new Buscar_produtos();
                buscarProdutos.request("categoria/" + categoria.getId(), "GET");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto prod = (Produto) parent.getItemAtPosition(position);
                ItemCarrinho item = new ItemCarrinho();
                for (int i = 0; i < listItemCarrinho.size(); i++) {
                    if (listItemCarrinho.get(i).getProduto().getId() == prod.getId()) {
                        item = listItemCarrinho.get(i);
                        break;
                    }
                }
                activAlert = new ActivAlertDialog(ActivLoja.this, prod, item);
                activAlert.show();
            }
        });

        upDate_view();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (spinner.getSelectedItem() == null) {
            Buscar_Categorias buscarCategorias = new Buscar_Categorias();
            buscarCategorias.request("categoria/{id}", "GET"); //categoriainfo
        }
    }

    public void displaySelectedCategory(Categoria categoria) {

        int id = categoria.getId();
        String nome = categoria.getNome();

        String cat_info = "ID: " + id + "\n" + "Nome: " + nome;

        Toast.makeText(this, cat_info, Toast.LENGTH_LONG).show();


    }

    public void upDate_view() {

        buscaCarrinho.request("/auth/carrinho", "GET", session.token());
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActivCarrinho.class);
        startActivity(intent);
    }
}
