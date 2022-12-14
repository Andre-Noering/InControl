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
	private Integer qtdeEstoque;

	@Column(name="qtde_alerta_estoque", nullable = false)
	private Integer qtdeAlertaEstoque;

	@Column(name = "ativo", nullable = false)
	private Boolean ativo = true;

	@ManyToOne
	@JoinColumn(name = "id_loja", referencedColumnName = "id", nullable = false)
	private LojaEntity loja;

	@OneToMany(mappedBy = "item")
	private List<ItemVendaEntity> vendas;

	@OneToMany(mappedBy = "fornecedor")
	private List<ItemFornecedorEntity> fornecedores;
}