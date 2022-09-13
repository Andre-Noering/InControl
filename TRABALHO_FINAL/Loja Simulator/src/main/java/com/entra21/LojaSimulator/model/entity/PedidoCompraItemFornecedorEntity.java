package com.entra21.LojaSimulator.model.entity;

import lombok.Data;



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

    @ManyToOne
    @JoinColumn (name = "id_item_fornecedor", referencedColumnName = "id")
    private ItemFornecedorEntity idItemFornecedor;

    @ManyToOne


    @JoinColumn(name = "id_pedido_compra", referencedColumnName = "id")
    private PedidoCompraEntity idPedidoCompra;
}
