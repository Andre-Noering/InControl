package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorDTO;
import com.entra21.LojaSimulator.model.entity.*;
import com.entra21.LojaSimulator.view.repository.ItemFornecedorRepository;
import com.entra21.LojaSimulator.view.repository.PedidoCompraItemFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class PedidoCompraItemFornecedorService {
    @Autowired
    private PedidoCompraItemFornecedorRepository pedidoCompraItemFornecedorRepository;

    @Autowired
    private ItemFornecedorService itemFornecedorService;

    @Autowired PedidoCompraService pedidoCompraService;

    public PedidoCompraItemFornecedorEntity getPedidoCompraItemFornecedorById(Long id){
        return pedidoCompraItemFornecedorRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra de item não encontrado!"));
    }


    public void save(PedidoCompraItemFornecedorDTO input) {
        PedidoCompraItemFornecedorEntity newEntity = new PedidoCompraItemFornecedorEntity();
        newEntity.setId(input.getId());
        newEntity.setQuantidade(input.getQtde());
        newEntity.setValorUnitario(input.getValor_unitario());
        newEntity.setItemFornecedor(itemFornecedorService.getItemFornecedorById(input.getId_item_fornecedor()));
        newEntity.setPedidoCompra(pedidoCompraService.getById(input.getId_pedido_compra()));
        pedidoCompraItemFornecedorRepository.save(newEntity);
    }
    public void delete(Long id){
        if(pedidoCompraItemFornecedorRepository.existsById(id)){
            pedidoCompraItemFornecedorRepository.deleteById(id);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!");
        }
    }
    public void update(Long id,Double valor_unitario, Integer qtde){
        PedidoCompraItemFornecedorEntity i = getPedidoCompraItemFornecedorById(id);
        if(valor_unitario!=null){
            i.setValorUnitario(valor_unitario);
        }
        if(qtde!=null){
            i.setQuantidade(qtde);
        }
    }

    public List<PedidoCompraItemFornecedorEntity> getAllByIdItemFornecedor(Long id){
        return pedidoCompraItemFornecedorRepository.findAllById(Collections.singleton(id));
    }

    public ItemFornecedorEntity getItemFornecedorById(Long id){
        return itemFornecedorService.getItemFornecedorById(id);
    }

    public PedidoCompraEntity getPedidoCompraById(Long id){
        return pedidoCompraService.getById(id);
    }
}
