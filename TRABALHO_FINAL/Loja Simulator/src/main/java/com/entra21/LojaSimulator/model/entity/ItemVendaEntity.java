package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item_venda")
public class ItemVendaEntity {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qtde", nullable = false)
    private int qtde;

    @Column(name = "valor_unitario", nullable = false)
    private Double valorUnitario;

    @ManyToOne
    @JoinColumn(name="id_item",referencedColumnName = "id")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name="id_venda",referencedColumnName = "id")
    private VendaEntity venda;
}
