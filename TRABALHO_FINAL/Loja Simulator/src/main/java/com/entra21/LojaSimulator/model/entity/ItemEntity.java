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
	
	@Column(name="nome", nullable = false)
	private String nome;
	
	@Column(name="valor", nullable = false)
	private Double valor;
	
	@Column(name="qtde_estoque", nullable = false)
	private Integer qtde_estoque;

	@Column(name="qtde_alerta_estoque", nullable = false)
	private Integer qtde_alerta_estoque;

	@ManyToOne
	@JoinColumn(name = "id_loja", referencedColumnName = "id", nullable = false)
	private LojaEntity loja;

	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id_item", nullable = false)
	private List<ItemVendaEntity> vendas;

	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id_item", nullable = false)
	private List<ItemFornecedorEntity> fornecedores;
}
