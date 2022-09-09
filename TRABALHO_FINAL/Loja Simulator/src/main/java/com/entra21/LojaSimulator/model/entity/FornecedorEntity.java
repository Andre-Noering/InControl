package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fornecedor")
public class FornecedorEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="razao_social")
    private String razao_social;

    @Column(name="cnpj")
    private String cnpj;

    @Column(name="contato")
    private String contato;

    @ManyToOne
    @JoinColumn(name="id_loja", referencedColumnName = "id")
    private LojaEntity loja;
}
