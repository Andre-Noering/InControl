package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="pedido_compra")
public class PedidoCompraEntity {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data")
	private LocalDate data;

	@OneToOne
	@JoinColumn(name = "id_funcionario", referencedColumnName = "id")
	private FuncionarioEntity funcionario;]

	@OneToMany
	@JoinColumn(name = "id" , referencedColumnName = "id_pedido_compra", nullable = false)
	private List<PedidoCompraItemFornecedorEntity> pedidosCompra;

}
