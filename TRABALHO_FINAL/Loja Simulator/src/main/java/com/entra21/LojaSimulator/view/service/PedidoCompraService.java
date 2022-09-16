package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.entity.ItemVendaEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import com.entra21.LojaSimulator.view.repository.PedidoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class PedidoCompraService {
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;
	@Autowired
	private FuncionarioService funcionarioService;

	public PedidoCompraEntity getById(Long id){
		return pedidoCompraRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra não encontrado!"));
	}
	public void save(PedidoCompraDTO pedidoCompraDTO){
		PedidoCompraEntity pedido = new PedidoCompraEntity();
		pedido.setFuncionario(funcionarioService.build(funcionarioService.findFuncById(pedidoCompraDTO.getId_funcionario())));
		pedido.setId(pedidoCompraDTO.getId());
		pedido.setData(pedidoCompraDTO.getData());
		pedidoCompraRepository.save(pedido);
	}

	public void delete(Long id){
		PedidoCompraEntity pedidoCompraEntity = getById(id);
		pedidoCompraRepository.delete(pedidoCompraEntity);
	}
	public PedidoCompraDTO update(Long id, LocalDate data){
		PedidoCompraEntity pedido = getById(id);
		if(data!=null){
			pedido.setData(data);
		}
		return this.getDTOById(id);
	}

	public PedidoCompraDTO getDTOById(Long id){
		PedidoCompraEntity pedido = pedidoCompraRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra não encontrado!"));
		return new PedidoCompraDTO(pedido.getId(), pedido.getData(), pedido.getFuncionario().getId());
	}
}
