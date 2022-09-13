package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="item")
public class ItemEntity {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="qtde_estoque")
	private int qtde_estoque;

	@Column(name="qtde_alerta_estoque")
	private int qtde_alerta_estoque;

	@ManyToOne
	@JoinColumn(name = "id_loja", referencedColumnName = "id")
	private LojaEntity loja;

	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id_item")
	private List<VendaEntity> vendas;
	
}
