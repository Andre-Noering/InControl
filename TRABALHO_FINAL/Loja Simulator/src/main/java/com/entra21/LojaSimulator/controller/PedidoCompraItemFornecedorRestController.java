package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorPayloadDTO;
import com.entra21.LojaSimulator.view.service.PedidoCompraItemFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{razaoSocial}/PCIF")
public class PedidoCompraItemFornecedorRestController {
    @Autowired
    private PedidoCompraItemFornecedorService pedidoCompraItemFornecedorService;

    @GetMapping("/pedido-compra-item-fornecedor/{id}")
    public PedidoCompraItemFornecedorDTO getDTO(@PathVariable Long id){
        return pedidoCompraItemFornecedorService.getDTOById(id);
    }
    @PostMapping("/adicionar")
    public void save(@RequestBody PedidoCompraItemFornecedorPayloadDTO dto){
        pedidoCompraItemFornecedorService.save(dto);
    }
    @PutMapping("/atualizar")
    public void uptade(@RequestBody PedidoCompraItemFornecedorDTO dto){
        pedidoCompraItemFornecedorService.update(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidoCompraItemFornecedorService.delete(id);
    }
}
