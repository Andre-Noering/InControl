package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "item_fornecedor")
public class ItemFornecedorEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_compra")
    private Double valorCompra;

    @ManyToOne
    @JoinColumn (name = "id_item", referencedColumnName = "id")
    private ItemEntity idItem;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
    private FornecedorEntity idFornecedor;

    @OneToMany
    @JoinColumn(name = "id" , referencedColumnName = "id_item_fornecedor", nullable = false)
    private List<PedidoCompraItemFornecedorEntity> itensFornecedor;

}
