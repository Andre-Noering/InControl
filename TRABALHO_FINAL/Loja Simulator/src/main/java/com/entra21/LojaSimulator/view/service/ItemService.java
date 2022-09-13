package com.entra21.LojaSimulator.view.service;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
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

	//Retorna todos os itens de uma loja
	public List<ItemDTO> getByAllByLoja(Long idLoja) {
		List<ItemEntity> list = itemRepository.findAllByLoja_Id(idLoja);
		return list.stream().map(i -> {
			ItemDTO dto = new ItemDTO(i.getId(), i.getNome(), i.getValor(), i.getQtde_estoque(), i.getQtde_alerta_estoque());
			return dto;
		}).collect(Collectors.toList());
	}

	public ItemDTO getById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return new ItemDTO(i.getId(), i.getNome(), i.getValor(), i.getQtde_estoque(), i.getQtde_alerta_estoque());
	}

//	public void save(ItemDTO input) {
//		ItemEntity newEntity = new ItemEntity(input.getId(), input.getNome(), input.getValor(), input.getQtde_estoque(), input.getQtde_alerta_estoque());
//		itemRepository.save(newEntity);
//	}

	public void delete(Long id) {
		itemRepository.deleteById(id);
	}

	public int getQtdeEstoqueById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		return i.getQtde_estoque();
	}

	public boolean alertaById(Long id) {
		ItemEntity i = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
		if (i.getQtde_estoque() <= i.getQtde_alerta_estoque()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean alertaByEntity(ItemEntity entity) {
		if (entity.getQtde_estoque() <= entity.getQtde_alerta_estoque()) {
			return true;
		} else {
			return false;
		}
	}
}
