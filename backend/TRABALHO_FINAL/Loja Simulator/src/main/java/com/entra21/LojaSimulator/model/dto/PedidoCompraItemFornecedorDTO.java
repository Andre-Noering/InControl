package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class PedidoCompraItemFornecedorDTO {
    private Long id;
    private Double valor_unitario;
    private Integer qtde;
    private ItemFornecedorEntity itemFornecedor;
    private PedidoCompraEntity pedidoCompra;

    public PedidoCompraItemFornecedorDTO(Long id, Double valor_unitario, Integer qtde, ItemFornecedorEntity itemFornecedor, PedidoCompraEntity pedidoCompra) {
        this.id = id;
        this.valor_unitario = valor_unitario;
        this.qtde = qtde;
        this.itemFornecedor = itemFornecedor;
        this.pedidoCompra = pedidoCompra;
    }
}
