package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="loja")
public class LojaEntity {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="razao_social")
    private String razao_social;

    @Column(name="cnpj")
    private String cnpj;

    @Column(name="contato")
    private String contato;

    @Column(name="valor_caixa")
    private Double valor_caixa;

    @OneToMany
    @JoinColumn(name="id_funcionario", referencedColumnName = "id")
    private List<FuncionarioEntity> funcionarios;

    @OneToMany
    @JoinColumn(name="id_funcionario", referencedColumnName = "id")
    private List<FuncionarioEntity> funcionarios;

}
