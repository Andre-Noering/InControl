package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorPayloadDTO;
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

    public PedidoCompraItemFornecedorEntity getById(Long id){
        return pedidoCompraItemFornecedorRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido de compra de item não encontrado!"));
    }

    public List<PedidoCompraItemFornecedorEntity> getAllByIdItemFornecedor(Long id){
        return pedidoCompraItemFornecedorRepository.findAllById(Collections.singleton(id));
    }

    public PedidoCompraItemFornecedorDTO getDTOById(Long id) {
        PedidoCompraItemFornecedorEntity entity = getById(id);
        return new PedidoCompraItemFornecedorDTO(entity.getId(), entity.getValorUnitario(), entity.getQuantidade(), entity.getItemFornecedor().getId(), entity.getPedidoCompra().getId());
    }

    public ItemFornecedorEntity getItemFornecedorById(Long id){
        return itemFornecedorService.getById(id);
    }

    public PedidoCompraEntity getPedidoCompraById(Long id){
        return pedidoCompraService.getById(id);
    }


    //POST
    public void save(PedidoCompraItemFornecedorPayloadDTO input) {
        PedidoCompraItemFornecedorEntity newPedidoCompraItemFornecedor = new PedidoCompraItemFornecedorEntity();
        newPedidoCompraItemFornecedor.setQuantidade(input.getQtde());
        newPedidoCompraItemFornecedor.setValorUnitario(input.getValorUnitario());
        newPedidoCompraItemFornecedor.setItemFornecedor(itemFornecedorService.getById(input.getIdItemFornecedor()));
        newPedidoCompraItemFornecedor.setPedidoCompra(pedidoCompraService.getById(input.getIdPedidoCompra()));
        pedidoCompraItemFornecedorRepository.save(newPedidoCompraItemFornecedor);
    }

    //PUT
    public void update(PedidoCompraItemFornecedorDTO pedidoCompraItemFornecedorDTO){
        PedidoCompraItemFornecedorEntity pedidoCompraItemFornecedorEntity = getById(pedidoCompraItemFornecedorDTO.getId());
        if(pedidoCompraItemFornecedorDTO.getValorUnitario()!=null){
            pedidoCompraItemFornecedorEntity.setValorUnitario(pedidoCompraItemFornecedorDTO.getValorUnitario());
        }
        if(pedidoCompraItemFornecedorDTO.getQtde()!=null){
            pedidoCompraItemFornecedorEntity.setQuantidade(pedidoCompraItemFornecedorDTO.getQtde());
        }
        pedidoCompraItemFornecedorRepository.save(pedidoCompraItemFornecedorEntity);
    }


    public void delete(Long id){
        PedidoCompraItemFornecedorEntity pedidoCompraItemFornecedorEntity = getById(id);
        pedidoCompraItemFornecedorRepository.delete(pedidoCompraItemFornecedorEntity);
    }




}
