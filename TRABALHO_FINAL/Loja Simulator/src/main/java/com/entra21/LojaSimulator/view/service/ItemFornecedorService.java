package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
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

    public ItemFornecedorEntity getItemFornecedorById(Long id){
        return itemFornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item de Fornecedor não encontrada!"));
    }
    public void save(ItemFornecedorDTO itemFornecedorDTO){
        ItemFornecedorEntity itemFornecedorEntity = new ItemFornecedorEntity();
        itemFornecedorEntity.setFornecedor(fornecedorService.getFornecedorById(itemFornecedorDTO.getId_fornecedor()));
        itemFornecedorEntity.setItem(itemService.build(itemService.getDTOById(itemFornecedorDTO.getId_item())));
        itemFornecedorEntity.setValorCompra(itemFornecedorDTO.getValor_compra());
        itemFornecedorEntity.setId(itemFornecedorDTO.getId());
        itemFornecedorEntity.setPedidosCompra(pedidoCompraItemFornecedorService.getAllByIdItemFornecedor(itemFornecedorDTO.getId_fornecedor()));
        itemFornecedorRepository.save(itemFornecedorEntity);
    }

    public void delete(Long id){
        ItemFornecedorEntity itemFornecedorEntity = getItemFornecedorById(id);
        itemFornecedorRepository.delete(itemFornecedorEntity);
    }

    public void update(Long id,Double valor_compra){
        ItemFornecedorEntity itemFornecedorEntity = getItemFornecedorById(id);
        if(valor_compra!=null){
            itemFornecedorEntity.setValorCompra(valor_compra);
        }
    }

    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorService.getFornecedorById(id);
    }
    public ItemEntity getItem(Long id){
        return itemService.getItemById(id);
    }
}
