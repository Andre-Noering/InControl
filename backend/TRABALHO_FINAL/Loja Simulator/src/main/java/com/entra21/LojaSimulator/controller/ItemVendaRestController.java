package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.view.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{razao_social}/vendas/{id}/itens")
public class ItemVendaRestController {
    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping
    public List<ItemVendaDTO> getItens(@PathVariable Long id){
        return itemVendaService.getAllByVenda(id);
    }

    @GetMapping("/{id_item}")
    public ItemVendaDTO getItem(@PathVariable Long id_item){
        return itemVendaService.getDTOById(id_item);
    }

    @GetMapping("/{id_item}/valor")
    public Double getValor(@PathVariable Long id_item){
        return itemVendaService.getValor(itemVendaService.getDTOById(id_item));
    }

    @PostMapping("/adicionar")
    public void save(@PathVariable ItemVendaDTO itemVendaDTO){
        itemVendaService.save(itemVendaDTO);
    }

    @PutMapping("/{id_item}")
    public void update(@RequestBody ItemVendaDTO itemVendaDTO){
        itemVendaService.update(itemVendaDTO);
    }

    @DeleteMapping("/{id_item}")
    public void delete(@PathVariable Long id_item){
        itemVendaService.delete(id_item);
    }

}
