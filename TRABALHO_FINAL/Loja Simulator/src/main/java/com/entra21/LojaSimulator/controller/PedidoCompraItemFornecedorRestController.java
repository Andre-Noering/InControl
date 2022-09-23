package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorDTO;
import com.entra21.LojaSimulator.view.service.PedidoCompraItemFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pcif")
public class PedidoCompraItemFornecedorRestController {
    @Autowired
    private PedidoCompraItemFornecedorService pedidoCompraItemFornecedorService;

    @GetMapping
    @RequestMapping("/pedido-compra-item-fornecedor/id")
    public PedidoCompraItemFornecedorDTO getDTO(@PathVariable(name = "id")Long id){
        return pedidoCompraItemFornecedorService.getDTOById(id);
    }
    @PostMapping
    @RequestMapping("/save-pedido-compra-item-fornecedor")
    public void saveVenda(@RequestBody PedidoCompraItemFornecedorDTO dto){
        pedidoCompraItemFornecedorService.save(dto);
    }
    @PutMapping
    @RequestMapping("/alteracao-pedido-compra-item-fornecedor")
    public void uptadeVenda(@RequestBody PedidoCompraItemFornecedorDTO dto){
        pedidoCompraItemFornecedorService.update(dto);
    }
    @DeleteMapping
    @RequestMapping("/delete-pedido-compra-item-fornecedor/id")
    public void delete(@PathVariable Long id) {
        pedidoCompraItemFornecedorService.delete(id);
    }
}
