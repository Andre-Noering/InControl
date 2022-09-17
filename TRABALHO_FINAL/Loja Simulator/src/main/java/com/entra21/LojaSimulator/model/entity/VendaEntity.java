package com.entra21.LojaSimulator.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "venda")
public class VendaEntity {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name="id_cliente",referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_venda",referencedColumnName = "id_pessoa", nullable = false)
    @JsonIgnore
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id_venda", nullable = false)
    private List<ItemVendaEntity> itens;


}
