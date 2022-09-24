package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class PedidoCompraItemFornecedorDTO {
    private Long id;
    private Double valorUnitario;
    private Integer qtde;
    private Long idItemFornecedor;
    private Long idPedidoCompra;

    public PedidoCompraItemFornecedorDTO(Long id, Double valor_unitario, Integer qtde,Long idItemFornecedor, Long idPedidoCompra) {
        this.id = id;
        this.valorUnitario = valor_unitario;
        this.qtde = qtde;
        this.idItemFornecedor = idItemFornecedor;
        this.idPedidoCompra = idPedidoCompra;
    }
}
