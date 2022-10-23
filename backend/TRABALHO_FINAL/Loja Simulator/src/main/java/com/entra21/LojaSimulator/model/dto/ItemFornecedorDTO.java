package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class ItemFornecedorDTO {
    private Double valorCompra;
    private Long id;
    private Long idItem;
    private Long idFornecedor;

    private String nome_item;
    private String nome_fornecedor;
    private boolean ativo;

    public ItemFornecedorDTO(Long id, Double valor_compra, Long id_item, Long id_fornecedor){
        this.id= id;
        this.valorCompra = valor_compra;
        this.idFornecedor = id_fornecedor;
        this.idItem = id_item;
    }

    public ItemFornecedorDTO(Double valor_compra, Long id, Long idItem, Long idFornecedor, String nome_item, String nome_fornecedor, boolean ativo) {
        this.valorCompra = valor_compra;
        this.id = id;
        this.idItem = idItem;
        this.idFornecedor = idFornecedor;
        this.nome_item = nome_item;
        this.nome_fornecedor = nome_fornecedor;
        this.ativo=ativo;
    }
    public ItemFornecedorDTO(){

    }
}
