package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import lombok.Data;

@Data
public class PedidoCompraItemFornecedorPayloadDTO {
    private Double valorUnitario;
    private Integer qtde;
    private Long idItemFornecedor;
    private Long idPedidoCompra;

    public PedidoCompraItemFornecedorPayloadDTO(Double valor_unitario, Integer qtde, Long id_item_fornecedor, Long id_pedido_compra) {
        this.valorUnitario = valor_unitario;
        this.qtde = qtde;
        this.idItemFornecedor = id_item_fornecedor;
        this.idPedidoCompra = id_pedido_compra;
    }
}
