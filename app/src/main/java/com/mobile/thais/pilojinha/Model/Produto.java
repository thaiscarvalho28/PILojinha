package com.mobile.thais.pilojinha.Model;

import java.util.List;

class Produto {
    private Long id_produto;
    private String nome_produto;
    private double preco_produto;
    private double custo_produto;
    private int quantidade_estoque;
    private List<Imagens> imagens;

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public double getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(double preco_produto) {
        this.preco_produto = preco_produto;
    }

    public double getCusto_produto() {
        return custo_produto;
    }

    public void setCusto_produto(double custo_produto) {
        this.custo_produto = custo_produto;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public List<Imagens> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagens> imagens) {
        this.imagens = imagens;
    }
}
