package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.entity.ItemVendaEntity;
import com.entra21.LojaSimulator.model.entity.VendaEntity;
import com.entra21.LojaSimulator.view.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemVendaService {
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ItemService itemService;
    @Autowired
    private VendaService vendaService;
    public void save(ItemVendaDTO itemVendaDTO){
        ItemVendaEntity i = new ItemVendaEntity();
        i.setItem(itemService.build(itemService.getById(itemVendaDTO.getId_item())));
        i.setQtde(itemVendaDTO.getQtde());
        i.setValor_unitario(itemVendaDTO.getValor_unitario());
        i.setVenda(vendaService.getVenda(itemVendaDTO.getId_venda()));
        itemVendaRepository.save(i);
    }

    public void delete(Long id){
        itemVendaRepository.deleteById(id);
    }
    public ItemVendaDTO update(Long id, Integer qtde, Double valor_unitario){
        ItemVendaEntity i = itemVendaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        if(qtde!=null){
            i.setQtde(qtde);
        }
        if(valor_unitario!=null){
            i.setValor_unitario(valor_unitario);
        }
        return this.getDTOById(id);
    }
    public ItemVendaEntity getById(Long id){
        return itemVendaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
    }

    public ItemVendaDTO getDTOById(Long id){
       ItemVendaEntity i =itemVendaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
       return new ItemVendaDTO(i.getId(), i.getQtde(), i.getValor_unitario(), i.getItem().getId(), i.getVenda().getId());
    }

}
