package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemVendaPayloadDTO {

    private Integer qtde;
    private Double valorUnitario;
    private Long idItem;
    private Long idVenda;

    public ItemVendaPayloadDTO(Integer qtde, Double valorUnitario, Long idItem, Long idVenda) {
        this.qtde = qtde;
        this.valorUnitario = valorUnitario;
        this.idItem = idItem;
        this.idVenda = idVenda;
    }
}
