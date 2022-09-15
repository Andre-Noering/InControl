package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemVendaDTO {
    public ItemVendaDTO(Long id, Integer qtde, Double valor_unitario, Long id_item, Long id_venda){
        this.id=id;
        this.qtde=qtde;
        this.valor_unitario = valor_unitario;
        this.id_item = id_item;
        this.id_venda = id_venda;
    }
    private Long id;
    private Integer qtde;
    private Double valor_unitario;
    private Long id_item;
    private Long id_venda;
}
