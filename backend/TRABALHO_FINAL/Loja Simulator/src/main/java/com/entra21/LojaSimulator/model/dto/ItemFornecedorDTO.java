package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemFornecedorDTO {
    private Double valor_compra;
    private Long id;
    private Long idItem;
    private Long idFornecedor;

    private String nome_item;
    private String nome_fornecedor;

    public ItemFornecedorDTO(Long id, Double valor_compra, Long id_item, Long id_fornecedor){
        this.id= id;
        this.valor_compra = valor_compra;
        this.idFornecedor = id_fornecedor;
        this.idItem = id_item;
    }
}
