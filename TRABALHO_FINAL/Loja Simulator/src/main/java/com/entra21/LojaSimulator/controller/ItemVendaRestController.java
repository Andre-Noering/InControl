package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.dto.ItemVendaPayloadDTO;
import com.entra21.LojaSimulator.view.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensVenda")
public class ItemVendaRestController {
    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping
    @RequestMapping("/itens")
    public List<ItemVendaDTO> getItens(@PathVariable Long id){
        return itemVendaService.getAllByVenda(id);
    }

    @GetMapping
    @RequestMapping("/{idItemVenda}")
    public ItemVendaDTO getItem(@PathVariable Long idItemVenda){
        return itemVendaService.getDTOById(idItemVenda);
    }

    @GetMapping
    @RequestMapping("/{id}/valor")
    public Double getValor(@PathVariable Long id){
        return itemVendaService.getValor(itemVendaService.getDTOById(id));
    }

    @PostMapping
    @RequestMapping("/adicionar")
    public void save(@RequestBody ItemVendaPayloadDTO itemVendaDTO){
        itemVendaService.save(itemVendaDTO);
    }

    @PutMapping
    @RequestMapping("/atualizar")
    public void update(@RequestBody ItemVendaDTO itemVendaDTO){
        itemVendaService.update(itemVendaDTO);
    }

    @DeleteMapping
    @RequestMapping("/{id}/deletar")
    public void delete(@PathVariable Long id){
        itemVendaService.delete(id);
    }

}
