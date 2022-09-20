package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.view.service.ItemService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/{razao_social}/itens")
public class ItemRestController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private LojaService lojaService;
	@GetMapping
	public List<ItemDTO> getAll(@PathVariable String razao_social){
		return itemService.getAllByLoja(lojaService.getByRazao_Social(razao_social).getId());
	}

	@GetMapping(name = "/{id}")
	public ItemDTO getItemById(@PathVariable Long id){
		return itemService.getDTOById(id);
	}

	@GetMapping(name="/alerta")
	public List<ItemDTO> getAllEmAlerta(@PathVariable String razao_social){
		return itemService.getItensEmAlerta(razao_social);
	}

	@PostMapping(name="/adicionar")
	public void save(@RequestBody ItemDTO itemDTO){
		itemService.save(itemDTO);
	}

	@PutMapping(name="/atualizar")
	public void update(@RequestBody ItemDTO itemDTO){
		itemService.update(itemDTO);
	}

	@DeleteMapping(name="/deletar")
	public void delete(@RequestBody Long id){
		itemService.delete(id);
	}

}
