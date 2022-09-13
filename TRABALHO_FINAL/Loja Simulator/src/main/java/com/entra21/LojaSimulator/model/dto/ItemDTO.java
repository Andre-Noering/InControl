package com.entra21.LojaSimulator.model.dto;

import lombok.Data;
import javax.persistence.Column;

@Data
public class ItemDTO {
    private Long id;
    private String nome;
    private Double valor;
    private Integer qtde_estoque;
    private Integer qtde_alerta_estoque;

    public ItemDTO(Long id, String nome, Double valor, int qtde_estoque, int qtde_alerta_estoque) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.qtde_estoque = qtde_estoque;
        this.qtde_alerta_estoque = qtde_alerta_estoque;
    }
}
