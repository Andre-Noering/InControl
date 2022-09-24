package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.view.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidosCompra")
public class PedidoCompraRestController {
	@Autowired
	private PedidoCompraService pedidoCompraService;

	@GetMapping
	@RequestMapping("/pedido-compra/id")
	public PedidoCompraDTO getDTO(@PathVariable(name = "id")Long id){
		return pedidoCompraService.getDTOById(id);
	}
	@PostMapping
	@RequestMapping("/adicionar")
	public void saveVenda(@RequestBody PedidoCompraPayloadDTO dto){
		pedidoCompraService.save(dto);
	}
	@PutMapping
	@RequestMapping("/atualizar")
	public void uptadeVenda(@RequestBody PedidoCompraDTO dto){
		pedidoCompraService.update(dto);
	}
	@DeleteMapping
	@RequestMapping("/id/deletar")
	public void delete(@PathVariable Long id) {
		pedidoCompraService.delete(id);
	}
}
