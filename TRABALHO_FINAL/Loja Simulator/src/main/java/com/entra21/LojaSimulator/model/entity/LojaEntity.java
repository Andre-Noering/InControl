package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name="loja")
public class LojaEntity {
    @Column(name="id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="razao_social", nullable = false)
    private String razaoSocial;

    @Column(name="cnpj", nullable = false)
    private String cnpj;

    @Column(name="contato", nullable = false)
    private String contato;

    @Column(name="valor_caixa", nullable = false)
    private Double valorCaixa;

    @OneToMany(mappedBy = "loja")
    private List<PessoaEntity> funcionarios;

    @ManyToOne
    @JoinColumn(name="id_funcionario", referencedColumnName = "id_pessoa", nullable = false)
    private FuncionarioEntity gerente;

    @OneToMany(mappedBy = "loja")
    private List<ItemEntity> itens;

    @OneToMany(mappedBy = "loja")
    private List<FornecedorEntity> fornecedores;
}