package com.mobile.thais.pilojinha.Model;

import java.util.Date;
import java.util.List;

public class Venda {
    private Long id_venda;
    private List<Produto> produtos;
    private Cliente cliente;
    private Date data_venda;
    private double valor_total_venda;

    public Long getId_venda() {
        return id_venda;
    }

    public void setId_venda(Long id_venda) {
        this.id_venda = id_venda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public double getValor_total_venda() {
        return valor_total_venda;
    }

    public void setValor_total_venda(double valor_total_venda) {
        this.valor_total_venda = valor_total_venda;
    }
}
