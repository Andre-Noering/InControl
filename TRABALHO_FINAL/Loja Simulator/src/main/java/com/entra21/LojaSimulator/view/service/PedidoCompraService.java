package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.view.repository.PedidoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoCompraService {
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;


}
