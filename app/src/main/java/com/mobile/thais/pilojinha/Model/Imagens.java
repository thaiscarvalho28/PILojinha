package com.mobile.thais.pilojinha.Model;

public class Imagens {
    private Long id;
    private String patch;
    private Produto produto;

    public Long getIdImg() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
