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

    @Column(name="data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    @JsonIgnore
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_venda",referencedColumnName = "id_pessoa")
    @JsonIgnore
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id_venda")
    private List<ItemVendaEntity> itens;


}
