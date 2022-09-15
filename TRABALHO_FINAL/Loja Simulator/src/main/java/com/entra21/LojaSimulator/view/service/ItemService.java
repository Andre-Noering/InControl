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
		itemRepository.deleteById(id);
	}

	public ItemDTO update(Long id, String novoNome, Double novoValor, Integer novaQtde, Integer novaQtdeAlerta) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		if (novoNome != null) {
			i.setNome(novoNome);
		}
		if (novoValor != null) {
			i.setValor(novoValor);
		}
		if (novaQtde != null) {
			i.setQtde_estoque(novaQtde);
		}
		if (novaQtdeAlerta != null) {
			i.setQtde_alerta_estoque(novaQtdeAlerta);
		}
		return new ItemDTO(i.getId(),i.getNome(),i.getValor(),i.getQtde_estoque(),i.getQtde_alerta_estoque());
	}

	//Retorna todos os itens de uma loja
	public List<ItemDTO> getAllByLoja(Long idLoja) {
		List<ItemEntity> list = itemRepository.findAllByLoja_Id(idLoja);
		return list.stream().map(i -> {
			ItemDTO dto = new ItemDTO(i.getId(), i.getNome(), i.getValor(), i.getQtde_estoque(), i.getQtde_alerta_estoque());
			return dto;
		}).collect(Collectors.toList());
	}

	//Retorna item pela id
	public ItemDTO getById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return new ItemDTO(i.getId(), i.getNome(), i.getValor(), i.getQtde_estoque(), i.getQtde_alerta_estoque());
	}

	//Retorna o valor do item pelo id
	public ItemValorDTO getValorById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return new ItemValorDTO(i.getValor());
	}

	//Retorna quantidade de estoque pelo id
	public ItemQtdeEstoqueDTO getQtdeEstoqueById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return new ItemQtdeEstoqueDTO(i.getQtde_estoque());
	}

	//Diz se o estoque está em alerta pelo id do item
	public boolean alertaById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		if (i.getQtde_estoque() <= i.getQtde_alerta_estoque()) {
			return true;
		} else {
			return false;
		}
	}

	public List<FornecedorEntity> getFornecedores(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return i.getFornecedores().stream().map(f -> {
			return itemFornecedorService.getFornById(f.getIdFornecedor());
		}).collect(Collectors.toList());
	}
}
