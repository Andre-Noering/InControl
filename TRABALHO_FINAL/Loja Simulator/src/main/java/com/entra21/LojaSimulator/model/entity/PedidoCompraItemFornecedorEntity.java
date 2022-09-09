package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import trabalhofinal.model.entity.PedidoCompraEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pedido_compra_item_fornecedor")
public class PedidoCompraItemFornecedorEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    @Column(name = "qtde")
    private Integer quantidade;

    @OneToMany
    @JoinColumn (name = "id_item_fornecedor", referencedColumnName = "id")
    private ItemFornecedorEntity idItemFornecedor;

    @OneToMany
    @JoinColumn(name = "id_pedido_compra", referencedColumnName = "id")
    private PedidoCompraEntity idPedidoCompra;
}
