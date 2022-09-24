package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import lombok.Data;

@Data
public class PedidoCompraItemFornecedorPayloadDTO {
    private Double valor_unitario;
    private Integer qtde;
    private Long id_item_fornecedor;
    private Long id_pedido_compra;

    public PedidoCompraItemFornecedorPayloadDTO(Double valor_unitario, Integer qtde, Long id_item_fornecedor, Long id_pedido_compra) {
        this.valor_unitario = valor_unitario;
        this.qtde = qtde;
        this.id_item_fornecedor = id_item_fornecedor;
        this.id_pedido_compra = id_pedido_compra;
    }
}
