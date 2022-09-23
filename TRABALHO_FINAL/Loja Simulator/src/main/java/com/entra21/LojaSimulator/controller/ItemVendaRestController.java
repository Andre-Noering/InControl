package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.view.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/razao_social/vendas/id")
public class ItemVendaRestController {
    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping
    @RequestMapping("/itens")
    public List<ItemVendaDTO> getItens(@PathVariable Long id){
        return itemVendaService.getAllByVenda(id);
    }

    @GetMapping
    @RequestMapping("/idItemVenda")
    public ItemVendaDTO getItem(@PathVariable Long idItemVenda){
        return itemVendaService.getDTOById(idItemVenda);
    }

    @GetMapping
    @RequestMapping("/id/valor")
    public Double getValor(@PathVariable Long id){
        return itemVendaService.getValor(itemVendaService.getDTOById(id));
    }

    @PostMapping
    @RequestMapping("/adicionarItemVenda")
    public void save(@PathVariable ItemVendaDTO itemVendaDTO){
        itemVendaService.save(itemVendaDTO);
    }

    @PutMapping
    @RequestMapping("/id/atualizar")
    public void update(@PathVariable Long id){
        itemVendaService.update(itemVendaService.getDTOById(id));
    }

    @DeleteMapping
    @RequestMapping("/id/deletar")
    public void delete(@PathVariable Long id){
        itemVendaService.delete(id);
    }

}
