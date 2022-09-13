package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="item")
public class ItemEntity {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable = false)
	private String nome;
	
	@Column(name="valor", nullable = false)
	private Double valor;
	
	@Column(name="qtde_estoque", nullable = false)
	private int qtde_estoque;

	@Column(name="qtde_alerta_estoque", nullable = false)
	private int qtde_alerta_estoque;

}
