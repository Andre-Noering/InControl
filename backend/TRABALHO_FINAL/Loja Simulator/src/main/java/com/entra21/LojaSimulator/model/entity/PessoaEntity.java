package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class PessoaEntity {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name="sobrenome", nullable = false)
    private String sobrenome;

    @Column(name="telefone", nullable = false)
    private String telefone;

    @Column(name="cpf", nullable = false)
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_loja" , referencedColumnName = "id")
    private LojaEntity loja;


}