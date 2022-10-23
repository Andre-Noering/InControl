package com.entra21.LojaSimulator.model.dto;

import lombok.Data;
import javax.persistence.Column;

@Data
public class ItemDTO {
    private Long id;
    private String nome;
    private Double valor;
    private Integer qtdeEstoque;
    private Integer qtdeAlertaEstoque;
    private boolean ativo;

    private Long idLoja;
    public ItemDTO(Long id, String nome, Double valor, int qtde_estoque, int qtde_alerta_estoque, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.qtdeEstoque = qtde_estoque;
        this.qtdeAlertaEstoque = qtde_alerta_estoque;
        this.ativo=ativo;
    }
}
