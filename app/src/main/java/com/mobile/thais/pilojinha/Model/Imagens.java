package com.mobile.thais.pilojinha.Model;

class Imagens {
    private Long id_img;
    private String nome_img;
    private Produto produto;

    public Long getId_img() {
        return id_img;
    }

    public void setId_img(Long id_img) {
        this.id_img = id_img;
    }

    public String getNome_img() {
        return nome_img;
    }

    public void setNome_img(String nome_img) {
        this.nome_img = nome_img;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
