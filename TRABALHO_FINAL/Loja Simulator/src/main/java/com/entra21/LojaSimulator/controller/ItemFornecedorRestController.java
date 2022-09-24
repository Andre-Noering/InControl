package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorPayloadDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.view.service.FornecedorService;
import com.entra21.LojaSimulator.view.service.ItemFornecedorService;
import com.entra21.LojaSimulator.view.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itensFornecedor")
public class ItemFornecedorRestController {
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ItemService itemService;

    @GetMapping
    @RequestMapping("/{idItemFornecedor}")
    public ItemFornecedorDTO itemFornecedorDTO(@PathVariable Long idItemFornecedor){
        return itemFornecedorService.getDTOById(idItemFornecedor);
    }

    @GetMapping
    @RequestMapping("/{id}/fornecedor-item")
    public FornecedorDTO fornecedorDTO(@PathVariable Long id){
        return fornecedorService.getDTOById(itemFornecedorService.getFornecedorById(id).getId());
    }

    @GetMapping
    @RequestMapping("/{id}/item-fornecedor")
    public ItemDTO itemDTO(@PathVariable Long id){
        return itemService.getDTOById(itemFornecedorService.getItem(id).getId());
    }

    @PostMapping
    @RequestMapping("/adicionar")
    public void save(@RequestBody ItemFornecedorPayloadDTO itemFornecedorDTO){
        itemFornecedorService.save(itemFornecedorDTO);
    }

    @PutMapping
    @RequestMapping("/atualizar")
    public void putItemFornecedor(@RequestBody ItemFornecedorDTO itemFornecedorDTO){
        itemFornecedorService.update(itemFornecedorDTO);
    }

    @DeleteMapping
    @RequestMapping("/{id}/deletar")
    public void deleteItemFornecedor(@PathVariable Long id){
        itemFornecedorService.delete(id);
    }
}
