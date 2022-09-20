package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.view.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/${razao_social}/vendas/${id}/itens")
public class ItemVendaRestController {
    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping
    public List<ItemVendaDTO> getItens(@PathVariable Long id){
        return itemVendaService.getAllByVenda(id);
    }

    @GetMapping(name="/${id}")
    public ItemVendaDTO getItem(@PathVariable Long id){
        return itemVendaService.getDTOById(id);
    }

    @GetMapping(name = "/${id}/valor")
    public Double getValor(@PathVariable Long id){
        return itemVendaService.getValor(itemVendaService.getDTOById(id));
    }

    @PostMapping(name = "/adicionar")
    public void save(@PathVariable ItemVendaDTO itemVendaDTO){
        itemVendaService.save(itemVendaDTO);
    }

    @PutMapping(name="/${id}")
    public void update(@PathVariable Long id){
        itemVendaService.update(itemVendaService.getDTOById(id));
    }

    @DeleteMapping(name="/${id}")
    public void delete(@PathVariable Long id){
        itemVendaService.delete(id);
    }

}
