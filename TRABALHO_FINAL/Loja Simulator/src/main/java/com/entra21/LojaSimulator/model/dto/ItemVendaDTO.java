package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemVendaDTO {
    private Long id;
    private int qtde;
    private Double valor_unitario;
    private Long id_item;
    private Long id_venda;
}
