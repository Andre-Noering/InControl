package com.entra21.LojaSimulator.view.service;
import com.entra21.LojaSimulator.model.dto.ItemAddDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	private LojaService lojaService;


	//GET
	public ItemEntity getItemById(Long id){
		return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item não encontrado!"));
	}


	//Retorna todos os itens de uma loja
	public List<ItemDTO> getAllByLoja(String razao_social) {
		List<ItemEntity> listaItens = lojaService.getByRazaoSocial(razao_social).getItens();
		listaItens.removeIf(item-> !item.getAtivo());
		return listaItens.stream().map(item -> {
			return new ItemDTO(item.getId(), item.getNome(), item.getValor(), item.getQtdeEstoque(), item.getQtdeAlertaEstoque(), item.getAtivo());
		}).collect(Collectors.toList());
	}

	//Retorna item pela id
	public ItemDTO getDTOById(Long id) {
		ItemEntity itemEntity = getItemById(id);
		return new ItemDTO(itemEntity.getId(), itemEntity.getNome(), itemEntity.getValor(), itemEntity.getQtdeEstoque(), itemEntity.getQtdeAlertaEstoque(), itemEntity.getAtivo());
	}

	//Retorna o valor do item pelo id
	public ItemValorDTO getValorById(Long id) {
		ItemEntity itemEntity = getItemById(id);
		return new ItemValorDTO(itemEntity.getValor());
	}
	public List<ItemDTO> getItensEmAlerta(String razaoSocial){
		List<ItemDTO> listaItens = getAllByLoja(razaoSocial);
		listaItens.removeIf(item -> !this.alertaById(item.getId()));
		return listaItens;
	}


	//Retorna quantidade de estoque pelo id
	public ItemQtdeEstoqueDTO getQtdeEstoqueById(Long id) {
		ItemEntity itemEntity =  getItemById(id);
		return new ItemQtdeEstoqueDTO(itemEntity.getQtdeEstoque());
	}

	//Diz se o estoque está em alerta pelo id do item
	public boolean alertaById(Long id) {
		ItemEntity itemEntity = getItemById(id);
		if (itemEntity.getQtdeEstoque() <= itemEntity.getQtdeAlertaEstoque()) {
			return true;
		} else {
			return false;
		}
	}

	public List<FornecedorEntity> getFornecedores(Long id) {
		ItemEntity itemEntity =  getItemById(id);
		return itemEntity.getFornecedores().stream().map(fornecedor -> {
			return itemFornecedorService.getFornecedorById(fornecedor.getFornecedor().getId());
		}).collect(Collectors.toList());
	}

	public ItemEntity build(ItemDTO input){
		ItemEntity newEntity = new ItemEntity();
		newEntity.setId(input.getId());
		newEntity.setNome(input.getNome());
		newEntity.setValor(input.getValor());
		newEntity.setQtdeEstoque(input.getQtdeEstoque());
		newEntity.setQtdeAlertaEstoque(input.getQtdeAlertaEstoque());
		return newEntity;
	}



	//POST
	public void save(@RequestBody ItemAddDTO input) {
		ItemEntity newEntity = new ItemEntity();
		newEntity.setNome(input.getNome());
		newEntity.setValor(input.getValor());
		newEntity.setQtdeEstoque(input.getQtdeEstoque());
		newEntity.setQtdeAlertaEstoque(input.getQtdeAlertaEstoque());
		newEntity.setLoja(lojaService.getById(input.getIdLoja()));
		itemRepository.save(newEntity);
	}

	//PUT
	public void update(ItemDTO itemDTO) {
		ItemEntity itemEntity = getItemById(itemDTO.getId());
		if (itemEntity.getNome() != null) {
			itemEntity.setNome(itemDTO.getNome());
		}
		if (itemEntity.getValor() != null) {
			itemEntity.setValor(itemDTO.getValor());
		}
		if (itemDTO.getQtdeEstoque() != null) {
			itemEntity.setQtdeEstoque(itemDTO.getQtdeEstoque());
		}
		if (itemDTO.getQtdeAlertaEstoque() != null) {
			itemEntity.setQtdeAlertaEstoque(itemDTO.getQtdeAlertaEstoque());
		}
		itemRepository.save(itemEntity);
	}


	public void delete(Long id) {
		ItemEntity itemEntity = getItemById(id);
		itemEntity.setAtivo(false);
		itemRepository.save(itemEntity);
	}


}
