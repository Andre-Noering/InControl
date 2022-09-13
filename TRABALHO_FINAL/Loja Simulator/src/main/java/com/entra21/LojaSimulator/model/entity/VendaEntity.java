package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "venda")
public class VendaEntity {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_vendedor",referencedColumnName = "id")
    private FuncionarioEntity funcionario;
}
