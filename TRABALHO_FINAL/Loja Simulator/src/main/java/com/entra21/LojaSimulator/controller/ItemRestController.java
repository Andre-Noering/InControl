package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.view.service.ItemService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{razao_social}/itens")
public class ItemRestController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private LojaService lojaService;
	@GetMapping
	public List<ItemDTO> getAll(@PathVariable String razao_social){
		return itemService.getAllByLoja(lojaService.getByRazaoSocial(razao_social).getId());
	}

	@GetMapping("/{idItem}")
	public ItemDTO getItemById(@PathVariable Long idItem){
		return itemService.getDTOById(idItem);
	}

	@GetMapping("/alerta")
	public List<ItemDTO> getAllEmAlerta(@PathVariable String razao_social){
		return itemService.getItensEmAlerta(razao_social);
	}

	@PostMapping("/adicionar")
	public void save(@RequestBody ItemDTO itemDTO){
		itemService.save(itemDTO);
	}

	@PutMapping("/atualizar")
	public void update(@RequestBody ItemDTO itemDTO){
		itemService.update(itemDTO);
	}

	@DeleteMapping("/{idItem}")
	public void delete(@PathVariable Long idItem){
		itemService.delete(idItem);
	}

}
