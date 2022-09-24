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
	public ItemEntity getById(Long id){
		return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item não encontrado!"));
	}


	//Retorna todos os itens de uma loja
	public List<ItemDTO> getAllByLoja(Long idLoja) {
		List<ItemEntity> listaItens = lojaService.getById(idLoja).getItens();
		return listaItens.stream().map(item -> {
			return new ItemDTO(item.getId(), item.getNome(), item.getValor(), item.getQtdeEstoque(), item.getQtdeAlertaEstoque(), item.getLoja().getId());
		}).collect(Collectors.toList());
	}

	//Retorna item pela id
	public ItemDTO getDTOById(Long id) {
		ItemEntity itemEntity = getById(id);
		return new ItemDTO(itemEntity.getId(), itemEntity.getNome(), itemEntity.getValor(), itemEntity.getQtdeEstoque(), itemEntity.getQtdeAlertaEstoque(), itemEntity.getLoja().getId());
	}

	//Retorna o valor do item pelo id
	public ItemValorDTO getValorById(Long id) {
		ItemEntity itemEntity = getById(id);
		return new ItemValorDTO(itemEntity.getValor());
	}
	public List<ItemDTO> getItensEmAlerta(String razaoSocial){
		List<ItemDTO> listaItens = getAllByLoja(lojaService.getByRazaoSocial(razaoSocial).getId());
		listaItens.removeIf(item -> !this.alertaById(item.getId()));
		return listaItens;
	}


	//Retorna quantidade de estoque pelo id
	public ItemQtdeEstoqueDTO getQtdeEstoqueById(Long id) {
		ItemEntity itemEntity =  getById(id);
		return new ItemQtdeEstoqueDTO(itemEntity.getQtdeEstoque());
	}

	//Diz se o estoque está em alerta pelo id do item
	public boolean alertaById(Long id) {
		ItemEntity itemEntity = getById(id);
		if (itemEntity.getQtdeEstoque() <= itemEntity.getQtdeAlertaEstoque()) {
			return true;
		} else {
			return false;
		}
	}

	public List<FornecedorEntity> getFornecedores(Long id) {
		ItemEntity itemEntity =  getById(id);
		return itemEntity.getFornecedores().stream().map(fornecedor -> {
			return itemFornecedorService.getFornecedorById(fornecedor.getFornecedor().getId());
		}).collect(Collectors.toList());
	}

	public ItemEntity build(ItemDTO input){
		ItemEntity newEntity = new ItemEntity();
		newEntity.setNome(input.getNome());
		newEntity.setValor(input.getValor());
		newEntity.setQtdeEstoque(input.getQtdeEstoque());
		newEntity.setQtdeAlertaEstoque(input.getQtdeAlertaEstoque());
		newEntity.setLoja(lojaService.getById(input.getIdLoja()));
		return newEntity;
	}

	public ItemEntity buildWithId(ItemDTO input){
		ItemEntity newEntity = new ItemEntity();
		newEntity.setId(input.getId());
		newEntity.setNome(input.getNome());
		newEntity.setValor(input.getValor());
		newEntity.setQtdeEstoque(input.getQtdeEstoque());
		newEntity.setQtdeAlertaEstoque(input.getQtdeAlertaEstoque());
		newEntity.setLoja(lojaService.getById(input.getIdLoja()));
		return newEntity;
	}



	//POST
	public void save(@RequestBody ItemDTO input) {
		itemRepository.save(build(input));
	}

	//PUT
	public void update(ItemDTO itemDTO) {
		ItemEntity itemEntity = getById(itemDTO.getId());
		if (itemEntity.getNome() != null) {
			itemEntity.setNome(itemEntity.getNome());
		}
		if (itemEntity.getValor() != 0.0) {
			itemEntity.setValor(itemEntity.getValor());
		}
		if (itemDTO.getQtdeEstoque() != 0) {
			itemEntity.setQtdeEstoque(itemDTO.getQtdeEstoque());
		}
		if (itemDTO.getQtdeAlertaEstoque() !=0) {
			itemEntity.setQtdeAlertaEstoque(itemDTO.getQtdeAlertaEstoque());
		}
		itemRepository.save(itemEntity);
	}


	public void delete(Long id) {
		ItemEntity itemEntity = getById(id);
		itemRepository.delete(itemEntity);
	}


}
