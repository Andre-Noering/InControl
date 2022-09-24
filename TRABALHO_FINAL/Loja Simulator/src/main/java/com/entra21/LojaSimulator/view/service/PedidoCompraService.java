package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraPayloadDTO;
import com.entra21.LojaSimulator.model.entity.ItemVendaEntity;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import com.entra21.LojaSimulator.model.entity.VendaEntity;
import com.entra21.LojaSimulator.view.repository.ItemRepository;
import com.entra21.LojaSimulator.view.repository.LojaRepository;
import com.entra21.LojaSimulator.view.repository.PedidoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PedidoCompraService {
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	private ItemRepository itemRepository;

	public PedidoCompraEntity getById(Long id){
		return pedidoCompraRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra não encontrado!"));
	}
	public PedidoCompraDTO getDTOById(Long id){
		PedidoCompraEntity pedidoCompraEntity = pedidoCompraRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra não encontrado!"));
		return new PedidoCompraDTO(pedidoCompraEntity.getId(), pedidoCompraEntity.getData(), pedidoCompraEntity.getFuncionario().getId());
	}
	public Double getValorTotal(Long id){
		PedidoCompraEntity pedidoCompraEntity= getById(id);
		AtomicReference<Double> valorTotal= new AtomicReference<>(0.0);
		pedidoCompraEntity.getPedidosCompra().forEach(itemPedido -> valorTotal.updateAndGet(v1 -> v1 + itemPedido.getValorUnitario() * itemPedido.getQuantidade()));
		return valorTotal.get();
	}
	//POST
	public void save(PedidoCompraPayloadDTO pedidoCompraDTO){
		PedidoCompraEntity pedido = new PedidoCompraEntity();
		pedido.setFuncionario(funcionarioService.build(funcionarioService.getDTOById(pedidoCompraDTO.getIdFuncionario())));
		pedido.setData(pedidoCompraDTO.getData());
		pedido = pedidoCompraRepository.save(pedido);
	}
	public void finish(Long id){
		PedidoCompraEntity pedido = getById(id);
		pedido.getFuncionario().getLoja().setValorCaixa(pedido.getFuncionario().getLoja().getValorCaixa()-getValorTotal(pedido.getId()));
		lojaRepository.save(pedido.getFuncionario().getLoja());
		pedido.getPedidosCompra().forEach(item->{item.getItemFornecedor().getItem().setQtdeEstoque(item.getItemFornecedor().getItem().getQtdeEstoque()+item.getQuantidade());
		itemRepository.save(item.getItemFornecedor().getItem());});
	}

	//PUT
	public void update(PedidoCompraDTO pedidoCompraDTO){
		PedidoCompraEntity pedidoCompraEntity = getById(pedidoCompraDTO.getId());
		if(pedidoCompraDTO.getData()!=null){
			pedidoCompraEntity.setData(pedidoCompraDTO.getData());
		}
		pedidoCompraRepository.save(pedidoCompraEntity);
	}


	public void delete(Long id){
		PedidoCompraEntity pedidoCompraEntity = getById(id);
		pedidoCompraRepository.delete(pedidoCompraEntity);
	}



}
