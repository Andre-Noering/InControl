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

@RestController
@RequestMapping(name = "/ItemFornecedor")
public class ItemFornecedorRestController {
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping(name = "/${id}")
    public ItemFornecedorDTO itemFornecedorDTO(@RequestBody Long id){
        return itemFornecedorService.getDTOById(id);
    }

    @GetMapping(name = "/${id}/fornecedor-item")
    public FornecedorDTO fornecedorDTO(@RequestBody Long id){
        return fornecedorService.getDTOById(itemFornecedorService.getFornecedorById(id).getId());
    }

    @GetMapping(name = "/${id}/item-fornecedor")
    public ItemDTO itemDTO(@RequestBody Long id){
        return itemFornecedorService.getItemDTO(id);
    }

    @PostMapping(name = "/save")
    public void saveItemFornecedor(@RequestBody ItemFornecedorDTO itemFornecedorDTO){
        itemFornecedorService.save(itemFornecedorDTO);
    }

    @PutMapping(name = "/put")
    public void putItemFornecedor(@RequestBody ItemFornecedorDTO itemFornecedorDTO){
        itemFornecedorService.update(itemFornecedorDTO);
    }

    @DeleteMapping(name = "/delete")
    public void deleteItemFornecedor(@RequestBody Long id){
        itemFornecedorService.delete(id);
    }
}
