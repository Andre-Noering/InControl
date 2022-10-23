package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorDTO;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import com.entra21.LojaSimulator.view.repository.ItemRepository;
import com.entra21.LojaSimulator.view.repository.LojaRepository;
import com.entra21.LojaSimulator.view.repository.PedidoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PedidoCompraService {
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private ItemService itemService;
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

	public List<PedidoCompraItemFornecedorDTO> getItens(Long id){
		return getById(id).getPedidosCompra().stream().map(item -> { PedidoCompraItemFornecedorDTO itemC =new PedidoCompraItemFornecedorDTO(item.getId(), item.getValorUnitario(), item.getQuantidade(), item.getItemFornecedor().getId(), item.getPedidoCompra().getId());
		itemC.setNomeItem(item.getItemFornecedor().getItem().getNome());
		itemC.setNomeFornecedor(item.getItemFornecedor().getFornecedor().getRazaoSocial()); return itemC;}).collect(Collectors.toList());
	}

	public Double getValorTotal(Long id){
		AtomicReference<Double> valor = new AtomicReference<>(0.0);
		this.getById(id).getPedidosCompra().stream().forEach(pedido -> valor.updateAndGet(v -> v + pedido.getItemFornecedor().getValorCompra()*pedido.getQuantidade()));
		return valor.get();
	}
	//POST
	public PedidoCompraDTO save(PedidoCompraDTO pedidoCompraDTO){
		PedidoCompraEntity pedido = new PedidoCompraEntity();
		pedido.setFuncionario(funcionarioService.build(funcionarioService.getDTOById(pedidoCompraDTO.getId_funcionario())));
		pedido.setId(pedidoCompraDTO.getId());
		pedido.setData(pedidoCompraDTO.getData());
		pedido = pedidoCompraRepository.save(pedido);
		return getDTOById(pedido.getId());
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
	}


	public void delete(Long id){
		PedidoCompraEntity pedidoCompraEntity = getById(id);
		pedidoCompraRepository.delete(pedidoCompraEntity);
	}



}
