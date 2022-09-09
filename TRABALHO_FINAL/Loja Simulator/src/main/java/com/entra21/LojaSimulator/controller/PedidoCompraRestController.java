package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.view.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoCompraRestController {
	@Autowired
	private PedidoCompraService pedidoCompraService;
}
