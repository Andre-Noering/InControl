package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemFornecedorPayloadDTO {

    private Double valorCompra;
    private Long idItem;
    private Long idFornecedor;

    public ItemFornecedorPayloadDTO( Double valor_compra, Long id_item, Long id_fornecedor){
        this.valorCompra = valor_compra;
        this.idFornecedor = id_fornecedor;
        this.idItem = id_item;
    }


}
