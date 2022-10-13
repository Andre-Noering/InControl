package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.view.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoCompraRestController {
	@Autowired
	private PedidoCompraService pedidoCompraService;

	@GetMapping(name = "/pedido-compra/${id}")
	public PedidoCompraDTO getDTO(@PathVariable(name = "id")Long id){
		return pedidoCompraService.getDTOById(id);
	}
	@PostMapping(name = "/save-pedido-compra")
	public void saveVenda(@RequestBody PedidoCompraDTO dto){
		pedidoCompraService.save(dto);
	}
	@PutMapping(name = "/alteracao-pedido-compra")
	public void uptadeVenda(@RequestBody PedidoCompraDTO dto){
		pedidoCompraService.update(dto);
	}
	@DeleteMapping(name = "delete-pedido-compra/${id}")
	public void delete(@PathVariable Long id) {
		pedidoCompraService.delete(id);
	}
}
