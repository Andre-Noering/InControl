package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.dto.VendaDTO;
import com.entra21.LojaSimulator.model.entity.ItemVendaEntity;
import com.entra21.LojaSimulator.model.entity.VendaEntity;
import com.entra21.LojaSimulator.view.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemVendaService {
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ItemService itemService;
    @Autowired
    private VendaService vendaService;

    //GET
    public ItemVendaEntity getItemVendaById(Long id){
        return itemVendaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
    }

    public List<ItemVendaDTO> getAllByVenda(Long id){
        return itemVendaRepository.findAllByVenda_Id(id).stream().map(item-> {return getDTOById(item.getId());}).collect(Collectors.toList());
    }
    public ItemVendaDTO getDTOById(Long id){
        ItemVendaEntity itemVendaEntity = getItemVendaById(id);
        return new ItemVendaDTO(itemVendaEntity.getId(), itemVendaEntity.getQtde(), itemVendaEntity.getValorUnitario(), itemVendaEntity.getItem().getId(), itemVendaEntity.getVenda().getId());
    }

    public ItemDTO getItemDTO(Long id){
        return itemService.getDTOById(getItemVendaById(id).getItem().getId());
    }
    public VendaDTO getVendaDTO(Long id){
        return vendaService.getDTOById(getItemVendaById(id).getVenda().getId());
    }

    public Double getValor(ItemVendaDTO itemVendaDTO){
        return itemVendaDTO.getValorUnitario()*itemVendaDTO.getQtde();
    }


    //POST
    public void save(ItemVendaDTO itemVendaDTO){
        ItemVendaEntity itemVendaEntity = new ItemVendaEntity();
        itemVendaEntity.setItem(itemService.build(itemService.getDTOById(itemVendaDTO.getIdItem())));
        itemVendaEntity.setQtde(itemVendaDTO.getQtde());
        itemVendaEntity.setValorUnitario(itemVendaDTO.getValorUnitario());
        itemVendaEntity.setVenda(vendaService.getVenda(itemVendaDTO.getIdVenda()));
        itemVendaRepository.save(itemVendaEntity);
    }

    //PUT
    public void update(ItemVendaDTO itemVendaDTO){
        ItemVendaEntity itemVendaEntity = getItemVendaById(itemVendaDTO.getId());
        if(itemVendaDTO.getQtde()!=null){
            itemVendaEntity.setQtde(itemVendaDTO.getQtde());
        }
        if(itemVendaDTO.getValorUnitario()!=null){
            itemVendaEntity.setValorUnitario(itemVendaDTO.getValorUnitario());
        }
    }
    public void updateQtde(Integer qtde_nova, Long id){
        getItemVendaById(id).setQtde(qtde_nova);
    }


    public void delete(Long id){
        ItemVendaEntity itemVendaEntity = getItemVendaById(id);
        itemVendaRepository.delete(itemVendaEntity);
    }



}
