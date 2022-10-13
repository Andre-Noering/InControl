package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="pedido_compra")
public class PedidoCompraEntity {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data", nullable = false)
	private LocalDateTime data;

	@ManyToOne
	@JoinColumn(name = "id_funcionario", referencedColumnName = "id_pessoa", nullable = false)
	private FuncionarioEntity funcionario;

	@OneToMany(mappedBy = "pedidoCompra")
	private List<PedidoCompraItemFornecedorEntity> pedidosCompra;
}
