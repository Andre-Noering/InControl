package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemPayloadDTO {
    private String nome;
    private Double valor;
    private int qtde;
}
