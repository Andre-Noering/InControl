package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
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

    public List<PedidoCompraItemFornecedorEntity> getAllByIdItemFornecedor(Long id){
        return pedidoCompraItemFornecedorRepository.findAllById(Collections.singleton(id));
    }

    public PedidoCompraItemFornecedorDTO getDTOById(Long id) {
        PedidoCompraItemFornecedorEntity entity = getPedidoCompraItemFornecedorById(id);
        return new PedidoCompraItemFornecedorDTO(entity.getId(), entity.getValorUnitario(), entity.getQuantidade(), entity.getItemFornecedor(), entity.getPedidoCompra());
    }

    public ItemFornecedorEntity getItemFornecedorById(Long id){
        return itemFornecedorService.getItemFornecedorById(id);
    }

    public PedidoCompraEntity getPedidoCompraById(Long id){
        return pedidoCompraService.getById(id);
    }


    //POST
    public void save(PedidoCompraItemFornecedorDTO input) {
        PedidoCompraItemFornecedorEntity newPedidoCompraItemFornecedor = new PedidoCompraItemFornecedorEntity();
        newPedidoCompraItemFornecedor.setId(input.getId());
        newPedidoCompraItemFornecedor.setQuantidade(input.getQtde());
        newPedidoCompraItemFornecedor.setValorUnitario(input.getValor_unitario());
        newPedidoCompraItemFornecedor.setItemFornecedor(itemFornecedorService.getItemFornecedorById(input.getItemFornecedor().getId()));
        newPedidoCompraItemFornecedor.setPedidoCompra(pedidoCompraService.getById(input.getPedidoCompra().getId()));
        pedidoCompraItemFornecedorRepository.save(newPedidoCompraItemFornecedor);
    }

    //PUT
    public void update(PedidoCompraItemFornecedorDTO pedidoCompraItemFornecedorDTO){
        PedidoCompraItemFornecedorEntity pedidoCompraItemFornecedorEntity = getPedidoCompraItemFornecedorById(pedidoCompraItemFornecedorDTO.getId());
        if(pedidoCompraItemFornecedorDTO.getValor_unitario()!=null){
            pedidoCompraItemFornecedorEntity.setValorUnitario(pedidoCompraItemFornecedorDTO.getValor_unitario());
        }
        if(pedidoCompraItemFornecedorDTO.getQtde()!=null){
            pedidoCompraItemFornecedorEntity.setQuantidade(pedidoCompraItemFornecedorDTO.getQtde());
        }
    }


    public void delete(Long id){
        PedidoCompraItemFornecedorEntity pedidoCompraItemFornecedorEntity = getPedidoCompraItemFornecedorById(id);
        pedidoCompraItemFornecedorRepository.delete(pedidoCompraItemFornecedorEntity);
    }




}
