package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemAddDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.view.service.ItemService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemRestController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private LojaService lojaService;
	@GetMapping("/{id_loja}")
	public List<ItemDTO> getAll(@PathVariable Long id_loja){
		return itemService.getAllByLoja(id_loja);
	}

	@GetMapping("/item/{id}")
	public ItemDTO getItemById(@PathVariable Long id){
		return itemService.getDTOById(id);
	}

	@GetMapping("/alerta")
	public List<ItemDTO> getAllEmAlerta(@RequestBody String razao_social){
		return itemService.getItensEmAlerta(razao_social);
	}

	@PostMapping("/adicionar")
	public void save(@RequestBody ItemAddDTO itemDTO){
		itemService.save(itemDTO);
	}

	@PutMapping("/atualizar")
	public void update(@RequestBody ItemDTO itemDTO){
		itemService.update(itemDTO);
	}

	@DeleteMapping("/deletar")
	public void delete(@RequestBody Long id){
		itemService.delete(id);
	}

}
