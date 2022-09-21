package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemVendaDTO {
    private Long id;
    private Integer qtde;
    private Double valorUnitario;
    private Long idItem;
    private Long idVenda;

    public ItemVendaDTO(Long id, Integer qtde, Double valorUnitario, Long idItem, Long idVenda) {
        this.id = id;
        this.qtde = qtde;
        this.valorUnitario = valorUnitario;
        this.idItem = idItem;
        this.idVenda = idVenda;
    }
}
