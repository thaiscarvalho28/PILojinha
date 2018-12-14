package com.mobile.thais.pilojinha.View;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.thais.pilojinha.Manipular_API.Buscar_Carrinho;
import com.mobile.thais.pilojinha.Manipular_API.Popular_Carrinho;
import com.mobile.thais.pilojinha.Model.ItemCarrinho;
import com.mobile.thais.pilojinha.Model.Produto;
import com.mobile.thais.pilojinha.Model.Session;
import com.mobile.thais.pilojinha.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivAlertDialog extends Dialog implements View.OnClickListener {

    public Activity activity;
    public Button mais, menos, continuar;
    TextView viewQuant;
    Buscar_Carrinho buscaCarrinho;
    Produto prod;
    ItemCarrinho item;
    double qtd = 0;


    public ActivAlertDialog(Activity act, Produto prod, ItemCarrinho item) {
        super(act);
        this.activity = act;
        this.prod = prod;
        this.item = item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_dialog);
        mais = findViewById(R.id.btnMais);
        menos = findViewById(R.id.btnMenos);
        continuar = findViewById(R.id.btnOkay);
        viewQuant = findViewById(R.id.viewQuantidade);

        mais.setOnClickListener(this);
        menos.setOnClickListener(this);
        continuar.setOnClickListener(this);

        continuar.setText(String.valueOf(item.getQuantidade()));
        qtd = item.getQuantidade();

        buscaCarrinho = new Buscar_Carrinho(activity, ActivLoja.txt_valor);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnMais) {
            qtd = qtd + 1;
            if (qtd > prod.getQnt()) {
                qtd--;
                Toast.makeText(activity, "Quantidade de produto insuficiente no estoque!", Toast.LENGTH_LONG).show();
            } else {
                viewQuant.setText(String.valueOf(qtd));
            }

        } else if (v.getId() == R.id.btnMenos) {
            qtd = qtd - 1;
            if (qtd < 0) {
                qtd++;
                Toast.makeText(activity, "O mínimo é Zero!", Toast.LENGTH_LONG).show();
            }
            viewQuant.setText(String.valueOf(qtd));


        } else if (v.getId() == R.id.btnOkay) {

            JSONObject prodJson = new JSONObject();
            Session session = new Session(activity);
            try {
                prodJson.put("id", prod.getId());
                prodJson.put("qnt", qtd);

                if (item.getId() == null) {
                    Popular_Carrinho popularCarrinho = new Popular_Carrinho();
                    popularCarrinho.request("auth/carrinho", "POST", prodJson, session.token(), activity);
                } else if (item.getId() != null && qtd > 0) {
                    Popular_Carrinho popularCarrinho = new Popular_Carrinho();
                    popularCarrinho.request("auth/carrinho", "PUT", prodJson, session.token(), activity);
                } else if (item.getId() != null && qtd == 0){
                    Popular_Carrinho popularCarrinho = new Popular_Carrinho();
                    popularCarrinho.request("auth/carrinho/" + prod.getId(), "DELETE", prodJson, session.token(), activity);
                }

                buscaCarrinho.request("auth/carrinho", "GET", session.token());

                this.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
