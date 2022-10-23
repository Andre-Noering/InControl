package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.view.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/pedido-compra")
public class PedidoCompraRestController {
	@Autowired
	private PedidoCompraService pedidoCompraService;

	@GetMapping( "/{id}")
	public PedidoCompraDTO getDTO(@PathVariable( "id")Long id){
		return pedidoCompraService.getDTOById(id);
	}
	@GetMapping("/{id}/itens")
	public List<PedidoCompraItemFornecedorDTO> getItens(@PathVariable Long id){
		return pedidoCompraService.getItens(id);
	}
	@PostMapping("/finalizar")
	public void finalizar(@RequestBody Long id){
		pedidoCompraService.finish(id);
	}
	@GetMapping("/{id}/valor-total")
	public Double getValorTotal(@PathVariable Long id){
		return getValorTotal(id);
	}
	@PostMapping( "/adicionar")
	public PedidoCompraDTO save(@RequestBody PedidoCompraDTO dto){
		return pedidoCompraService.save(dto);
	}
	@PutMapping( "/update")
	public void update(@RequestBody PedidoCompraDTO dto){
		pedidoCompraService.update(dto);
	}
	@DeleteMapping( "/delete/{id}")
	public void delete(@PathVariable Long id) {
		pedidoCompraService.delete(id);
	}
}
