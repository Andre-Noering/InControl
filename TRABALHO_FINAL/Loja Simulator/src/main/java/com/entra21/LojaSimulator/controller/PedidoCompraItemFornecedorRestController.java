package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.view.service.PedidoCompraItemFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoCompraItemFornecedorRestController {
    @Autowired
    private PedidoCompraItemFornecedorService pedidoCompraItemFornecedorService;
}
