package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemFornecedorDTO {
    private Double valorCompra;
    private Long id;
    private Long idItem;
    private Long idFornecedor;

    public ItemFornecedorDTO(Long id, Double valor_compra, Long id_item, Long id_fornecedor){
        this.id= id;
        this.valorCompra = valor_compra;
        this.idFornecedor = id_fornecedor;
        this.idItem = id_item;
    }
}
