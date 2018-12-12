package com.mobile.thais.pilojinha.Model;

import java.util.Date;
import java.util.List;

public class Carrinho {
    private Long id_Carrinho;
    private List<ItemCarrinho> itensCarrinho;
    private Date expireTime;
    private Cliente cliente;
    private double valor_total;

    public Long getId_Carrinho() {
        return id_Carrinho;
    }

    public void setId_Carrinho(Long id_Carrinho) {
        this.id_Carrinho = id_Carrinho;
    }

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }
}
