package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class PedidoCompraItemFornecedorDTO {
    private Long id;
    private Double valor_unitario;
    private Integer qtde;
    private Long id_item_fornecedor;
    private Long id_pedido_compra;
}
