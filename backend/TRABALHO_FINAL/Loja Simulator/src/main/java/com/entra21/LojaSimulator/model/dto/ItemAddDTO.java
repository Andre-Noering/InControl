package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemAddDTO {
    private String nome;
    private Double valor;
    private Integer qtdeEstoque;
    private Integer qtdeAlertaEstoque;

    private Long idLoja;
    public ItemAddDTO( String nome, Double valor, int qtde_estoque, int qtde_alerta_estoque) {
        this.nome = nome;
        this.valor = valor;
        this.qtdeEstoque = qtde_estoque;
        this.qtdeAlertaEstoque = qtde_alerta_estoque;
    }
}
