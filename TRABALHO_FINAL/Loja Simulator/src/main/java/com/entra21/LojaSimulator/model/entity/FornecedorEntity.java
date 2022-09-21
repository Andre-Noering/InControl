package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="fornecedor")

public class FornecedorEntity {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="razao_social", nullable = false)
    private String razaoSocial;

    @Column(name="cnpj", nullable = false)
    private String cnpj;

    @Column(name="contato", nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name="id_loja", referencedColumnName = "id", nullable = false)
    private LojaEntity loja;

    @OneToMany
    @JoinColumn(name = "id" , referencedColumnName = "id_fornecedor", nullable = false)
    private List<ItemFornecedorEntity> itens;
}
