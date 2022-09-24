package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemFornecedorPayloadDTO {

    private Double valor_compra;
    private Long id_item;
    private Long id_fornecedor;

    public ItemFornecedorPayloadDTO( Double valor_compra, Long id_item, Long id_fornecedor){
        this.valor_compra = valor_compra;
        this.id_fornecedor = id_fornecedor;
        this.id_item = id_item;
    }


}
