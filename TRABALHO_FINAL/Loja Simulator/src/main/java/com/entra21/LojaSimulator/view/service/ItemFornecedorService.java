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
    public void save(ItemFornecedorDTO itemFornecedorDTO){
        ItemFornecedorEntity i = new ItemFornecedorEntity();
        i.setFornecedor(fornecedorService.getFornecedorById(itemFornecedorDTO.getId_fornecedor()));
        i.setItem(itemService.build(itemService.getById(itemFornecedorDTO.getId_item())));
        i.setValorCompra(itemFornecedorDTO.getValor_compra());
        i.setId(itemFornecedorDTO.getId());
        i.setPedidosCompra(pedidoCompraItemFornecedorService.getAllByIdItemFornecedor(itemFornecedorDTO.getId_fornecedor()));
        itemFornecedorRepository.save(i);
    }

    public void delete(Long id){
        itemFornecedorRepository.deleteById(id);
    }

    public void update(Long id,Double valor_compra){
        ItemFornecedorEntity i = itemFornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
        if(valor_compra!=null){
            i.setValorCompra(valor_compra);
        }
    }

    public ItemFornecedorEntity getById(Long id){
        return itemFornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }
    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorService.getFornecedorById(id);
    }
    public ItemEntity getItem(Long id){
        return itemService.build(itemService.getById(id));
    }
}
