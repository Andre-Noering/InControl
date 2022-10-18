package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.view.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "pedidosCompra")
public class PedidoCompraRestController {
	@Autowired
	private PedidoCompraService pedidoCompraService;

	@GetMapping( "/pedido-compra/{id}")
	public PedidoCompraDTO getDTO(@PathVariable( "id")Long id){
		return pedidoCompraService.getDTOById(id);
	}
	@PostMapping( "/save-pedido-compra")
	public void saveVenda(@RequestBody PedidoCompraDTO dto){
		pedidoCompraService.save(dto);
	}
	@PutMapping( "/alteracao-pedido-compra")
	public void uptadeVenda(@RequestBody PedidoCompraDTO dto){
		pedidoCompraService.update(dto);
	}
	@DeleteMapping( "delete-pedido-compra/{id}")
	public void delete(@PathVariable Long id) {
		pedidoCompraService.delete(id);
	}
}
