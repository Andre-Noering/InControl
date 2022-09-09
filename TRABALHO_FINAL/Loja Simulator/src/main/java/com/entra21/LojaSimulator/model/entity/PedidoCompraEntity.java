package com.entra21.LojaSimulator.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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
}
