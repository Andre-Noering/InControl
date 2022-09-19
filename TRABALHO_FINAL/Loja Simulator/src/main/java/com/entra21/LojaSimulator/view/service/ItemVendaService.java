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
        ItemVendaEntity itemVendaEntity = new ItemVendaEntity();
        itemVendaEntity.setItem(itemService.build(itemService.getDTOById(itemVendaDTO.getId_item())));
        itemVendaEntity.setQtde(itemVendaDTO.getQtde());
        itemVendaEntity.setValor_unitario(itemVendaDTO.getValor_unitario());
        itemVendaEntity.setVenda(vendaService.getVenda(itemVendaDTO.getId_venda()));
        itemVendaRepository.save(itemVendaEntity);
    }

    public void delete(Long id){
        ItemVendaEntity itemVendaEntity = getItemVenda(id);
        itemVendaRepository.delete(itemVendaEntity);
    }
    public void update(Long id, Integer qtde, Double valor_unitario){
        ItemVendaEntity itemVendaEntity = getItemVenda(id);
        if(qtde!=null){
            itemVendaEntity.setQtde(qtde);
        }
        if(valor_unitario!=null){
            itemVendaEntity.setValor_unitario(valor_unitario);
        }
    }
    public void updateQtde(Integer qtde_nova, Long id){
        getItemVenda(id).setQtde(qtde_nova);
    }
    public ItemVendaEntity getItemVenda(Long id){
        return itemVendaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
    }

    public ItemVendaDTO getDTOById(Long id){
       ItemVendaEntity itemVendaEntity = getItemVenda(id);
       return new ItemVendaDTO(itemVendaEntity.getId(), itemVendaEntity.getQtde(), itemVendaEntity.getValor_unitario(), itemVendaEntity.getItem().getId(), itemVendaEntity.getVenda().getId());
    }

}
