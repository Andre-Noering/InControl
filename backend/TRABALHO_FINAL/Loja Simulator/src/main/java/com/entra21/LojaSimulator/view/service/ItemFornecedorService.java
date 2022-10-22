package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.view.repository.FornecedorRepository;
import com.entra21.LojaSimulator.view.repository.ItemFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemFornecedorService {
    @Autowired
    private ItemFornecedorRepository itemFornecedorRepository;

    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ItemService itemService;
    @Autowired PedidoCompraItemFornecedorService pedidoCompraItemFornecedorService;

    //GET
    public ItemFornecedorEntity getItemFornecedorById(Long id){
        return itemFornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item de Fornecedor não encontrada!"));
    }

    public ItemFornecedorDTO getDTOById(Long id){
        ItemFornecedorEntity itemFornecedorEntity = getItemFornecedorById(id);
        return new ItemFornecedorDTO( itemFornecedorEntity.getValorCompra(),itemFornecedorEntity.getId(), itemFornecedorEntity.getItem().getId(), itemFornecedorEntity.getFornecedor().getId(), itemFornecedorEntity.getItem().getNome(), itemFornecedorEntity.getFornecedor().getRazaoSocial());
    }

    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorService.getFornecedorById(getItemFornecedorById(id).getFornecedor().getId());
    }

    public ItemEntity getItem(Long id){
        return itemService.getItemById(getItemFornecedorById(id).getItem().getId());
    }

    public ItemDTO getItemDTO(Long id){
        return itemService.getDTOById(this.getItem(id).getId());
    }


    //POST
    public void save(ItemFornecedorDTO itemFornecedorDTO){
        ItemFornecedorEntity itemFornecedorEntity = new ItemFornecedorEntity();
        itemFornecedorEntity.setFornecedor(fornecedorService.getFornecedorById(itemFornecedorDTO.getIdFornecedor()));
        itemFornecedorEntity.setItem(itemService.build(itemService.getDTOById(itemFornecedorDTO.getIdItem())));
        itemFornecedorEntity.setValorCompra(itemFornecedorDTO.getValorCompra());
        itemFornecedorEntity.setId(itemFornecedorDTO.getId());
        itemFornecedorEntity.setPedidosCompra(pedidoCompraItemFornecedorService.getAllByIdItemFornecedor(itemFornecedorDTO.getIdFornecedor()));
        itemFornecedorRepository.save(itemFornecedorEntity);
    }

    //PUT
    public void update(ItemFornecedorDTO itemFornecedorDTO){
        ItemFornecedorEntity itemFornecedorEntity = getItemFornecedorById(itemFornecedorDTO.getId());
        if(itemFornecedorDTO.getValorCompra() !=null){
            itemFornecedorEntity.setValorCompra(itemFornecedorDTO.getValorCompra());
        }
    }

    //DELETE
    public void delete(Long id){
        ItemFornecedorEntity itemFornecedorEntity = getItemFornecedorById(id);
        itemFornecedorRepository.delete(itemFornecedorEntity);
    }




}
