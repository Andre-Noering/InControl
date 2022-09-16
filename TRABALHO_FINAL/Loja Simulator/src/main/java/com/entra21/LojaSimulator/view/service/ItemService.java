package com.entra21.LojaSimulator.view.service;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemQtdeEstoqueDTO;
import com.entra21.LojaSimulator.model.dto.ItemValorDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import com.entra21.LojaSimulator.view.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemFornecedorService itemFornecedorService;

	public ItemEntity getItemById(Long id){
		return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item não encontrada!"));
	}

	public void save(ItemDTO input) {
		ItemEntity newEntity = new ItemEntity();
		newEntity.setId(input.getId());
		newEntity.setNome(input.getNome());
		newEntity.setValor(input.getValor());
		newEntity.setQtde_estoque(input.getQtde_estoque());
		newEntity.setQtde_alerta_estoque(input.getQtde_alerta_estoque());
		itemRepository.save(newEntity);
	}
	public ItemEntity build(ItemDTO input){
		ItemEntity newEntity = new ItemEntity();
		newEntity.setId(input.getId());
		newEntity.setNome(input.getNome());
		newEntity.setValor(input.getValor());
		newEntity.setQtde_estoque(input.getQtde_estoque());
		newEntity.setQtde_alerta_estoque(input.getQtde_alerta_estoque());
		return newEntity;
	}

	public void delete(Long id) {
		ItemEntity itemEntity = getItemById(id);
		itemRepository.delete(itemEntity);
	}

	public ItemDTO update(Long id, String novoNome, Double novoValor, Integer novaQtde, Integer novaQtdeAlerta) {
		ItemEntity itemEntity = getItemById(id);
		if (novoNome != null) {
			itemEntity.setNome(novoNome);
		}
		if (novoValor != null) {
			itemEntity.setValor(novoValor);
		}
		if (novaQtde != null) {
			itemEntity.setQtde_estoque(novaQtde);
		}
		if (novaQtdeAlerta != null) {
			itemEntity.setQtde_alerta_estoque(novaQtdeAlerta);
		}
		return new ItemDTO(itemEntity.getId(),itemEntity.getNome(),itemEntity.getValor(),itemEntity.getQtde_estoque(),itemEntity.getQtde_alerta_estoque());
	}

	//Retorna todos os itens de uma loja
	public List<ItemDTO> getAllByLoja(Long idLoja) {
		List<ItemEntity> listaItens = itemRepository.findAllByLoja_Id(idLoja);
		return listaItens.stream().map(item -> {
			ItemDTO itemDTO = new ItemDTO(item.getId(), item.getNome(), item.getValor(), item.getQtde_estoque(), item.getQtde_alerta_estoque());
			return itemDTO;
		}).collect(Collectors.toList());
	}

	//Retorna item pela id
	public ItemDTO getDTOById(Long id) {
		ItemEntity itemEntity = getItemById(id);
		return new ItemDTO(itemEntity.getId(), itemEntity.getNome(), itemEntity.getValor(), itemEntity.getQtde_estoque(), itemEntity.getQtde_alerta_estoque());
	}

	//Retorna o valor do item pelo id
	public ItemValorDTO getValorById(Long id) {
		ItemEntity itemEntity = getItemById(id);
		return new ItemValorDTO(itemEntity.getValor());
	}

	//Retorna quantidade de estoque pelo id
	public ItemQtdeEstoqueDTO getQtdeEstoqueById(Long id) {
		ItemEntity itemEntity =  getItemById(id);
		return new ItemQtdeEstoqueDTO(itemEntity.getQtde_estoque());
	}

	//Diz se o estoque está em alerta pelo id do item
	public boolean alertaById(Long id) {
		ItemEntity itemEntity = getItemById(id);
		if (itemEntity.getQtde_estoque() <= itemEntity.getQtde_alerta_estoque()) {
			return true;
		} else {
			return false;
		}
	}

	public List<FornecedorEntity> getFornecedores(Long id) {
		ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return itemEntity.getFornecedores().stream().map(fornecedor -> {
			return itemFornecedorService.getFornecedorById(fornecedor.getFornecedor().getId());
		}).collect(Collectors.toList());
	}
}
