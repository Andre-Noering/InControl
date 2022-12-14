package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.view.service.FornecedorService;
import com.entra21.LojaSimulator.view.service.ItemFornecedorService;
import com.entra21.LojaSimulator.view.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ItemFornecedor")
public class ItemFornecedorRestController {
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private FornecedorService fornecedorService;



    @GetMapping("/{id}")
    public ItemFornecedorDTO itemFornecedorDTO(@RequestBody Long id){
        return itemFornecedorService.getDTOById(id);
    }

    @GetMapping("/{id}/fornecedor-item")
    public FornecedorDTO fornecedorDTO(@RequestBody Long id){
        return fornecedorService.getDTOById(itemFornecedorService.getFornecedorById(id).getId());
    }

    @GetMapping("/{id}/item-fornecedor")
    public ItemDTO itemDTO(@PathVariable Long id){
        return itemFornecedorService.getItemDTO(id);
    }

    @PostMapping("/save")
    public void saveItemFornecedor(@RequestBody ItemFornecedorDTO itemFornecedorDTO){
        itemFornecedorService.save(itemFornecedorDTO);
    }

    @PutMapping("/atualizar")
    public void putItemFornecedor(@RequestBody ItemFornecedorDTO itemFornecedorDTO){
        itemFornecedorService.update(itemFornecedorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deleteItemFornecedor(@PathVariable Long id){
        itemFornecedorService.delete(id);
    }
}
