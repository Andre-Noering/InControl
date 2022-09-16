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
        ItemFornecedorEntity i = new ItemFornecedorEntity();
        i.setFornecedor(fornecedorService.getFornecedorById(itemFornecedorDTO.getId_fornecedor()));
        i.setItem(itemService.build(itemService.getDTOById(itemFornecedorDTO.getId_item())));
        i.setValorCompra(itemFornecedorDTO.getValor_compra());
        i.setId(itemFornecedorDTO.getId());
        i.setPedidosCompra(pedidoCompraItemFornecedorService.getAllByIdItemFornecedor(itemFornecedorDTO.getId_fornecedor()));
        itemFornecedorRepository.save(i);
    }

    public void delete(Long id){
        ItemFornecedorEntity itemFornecedorEntity = getItemFornecedorById(id);
        itemFornecedorRepository.delete(itemFornecedorEntity);
    }

    public void update(Long id,Double valor_compra){
        ItemFornecedorEntity i = getItemFornecedorById(id);
        if(valor_compra!=null){
            i.setValorCompra(valor_compra);
        }
    }

    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorService.getFornecedorById(id);
    }
    public ItemEntity getItem(Long id){
        return itemService.getItemById(id);
    }
}
