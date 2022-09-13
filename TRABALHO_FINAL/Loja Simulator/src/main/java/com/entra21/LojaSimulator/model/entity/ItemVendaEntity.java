package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "item_venda")
public class ItemVendaEntity {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qtde")
    private int qtde;

    @Column(name = "valor_unitario")
    private Double valor_unitario;

    @OneToMany(mappedBy = "itens")
    private ItemEntity item;

    @OneToMany(mappedBy = "vendas")
    private VendaEntity venda;
}
