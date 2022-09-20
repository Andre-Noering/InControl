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
	public PedidoCompraDTO getDTOById(Long id){
		PedidoCompraEntity pedidoCompraEntity = pedidoCompraRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra não encontrado!"));
		return new PedidoCompraDTO(pedidoCompraEntity.getId(), pedidoCompraEntity.getData(), pedidoCompraEntity.getFuncionario().getId());
	}

	//POST
	public void save(PedidoCompraDTO pedidoCompraDTO){
		PedidoCompraEntity pedido = new PedidoCompraEntity();
		pedido.setFuncionario(funcionarioService.build(funcionarioService.getDTOById(pedidoCompraDTO.getId_funcionario())));
		pedido.setId(pedidoCompraDTO.getId());
		pedido.setData(pedidoCompraDTO.getData());
		pedidoCompraRepository.save(pedido);
	}

	//PUT
	public void update(PedidoCompraDTO pedidoCompraDTO){
		PedidoCompraEntity pedidoCompraEntity = getById(pedidoCompraDTO.getId());
		if(pedidoCompraDTO.getData()!=null){
			pedidoCompraEntity.setData(pedidoCompraDTO.getData());
		}
	}


	public void delete(Long id){
		PedidoCompraEntity pedidoCompraEntity = getById(id);
		pedidoCompraRepository.delete(pedidoCompraEntity);
	}



}
