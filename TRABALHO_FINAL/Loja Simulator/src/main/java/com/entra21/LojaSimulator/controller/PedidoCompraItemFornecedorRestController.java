package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.PedidoCompraDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorDTO;
import com.entra21.LojaSimulator.model.dto.PedidoCompraItemFornecedorPayloadDTO;
import com.entra21.LojaSimulator.view.service.PedidoCompraItemFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PCIF")
public class PedidoCompraItemFornecedorRestController {
    @Autowired
    private PedidoCompraItemFornecedorService pedidoCompraItemFornecedorService;

    @GetMapping
    @RequestMapping("/pedido-compra-item-fornecedor/id")
    public PedidoCompraItemFornecedorDTO getDTO(@PathVariable(name = "id")Long id){
        return pedidoCompraItemFornecedorService.getDTOById(id);
    }
    @PostMapping
    @RequestMapping("/adicionar")
    public void saveVenda(@RequestBody PedidoCompraItemFornecedorPayloadDTO dto){
        pedidoCompraItemFornecedorService.save(dto);
    }
    @PutMapping
    @RequestMapping("/atualizar")
    public void uptadeVenda(@RequestBody PedidoCompraItemFornecedorDTO dto){
        pedidoCompraItemFornecedorService.update(dto);
    }
    @DeleteMapping
    @RequestMapping("/id/deletar")
    public void delete(@PathVariable Long id) {
        pedidoCompraItemFornecedorService.delete(id);
    }
}
