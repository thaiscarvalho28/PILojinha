package com.mobile.thais.pilojinha.Model;

public class ItemCarrinho {
    private Long id_item;
    private Produto produto;
    private double quantidade;
    private Carrinho carrinho;


    public Long getId() {
        return id_item;
    }

    public void setId(Long id) {
        this.id_item = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
