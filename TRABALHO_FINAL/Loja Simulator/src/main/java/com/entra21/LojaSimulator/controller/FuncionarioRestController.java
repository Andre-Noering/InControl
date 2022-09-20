package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FuncionarioDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioVendaDTO;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/funcionario")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService funcionarioService;
    @GetMapping(name = "/vendas")
    public FuncionarioVendaDTO getFuncionarioVendaDTO(Long id){
        return funcionarioService.getVendasFuncionario(id);
    }

    @GetMapping(name = "/${id_funcionario}")
    public FuncionarioDTO getFuncionarioDTO(@PathVariable(name = "id")Long id){
        return funcionarioService.findFuncById(id);
    }

    @PostMapping(name = "/save-venda")
    public void saveVenda(@RequestBody Long id){
         funcionarioService.saveVenda(id);
    }

    @PostMapping(name = "/save-pedido-compra")
    public void savePedidoCompra(@RequestBody Long id){
        funcionarioService.savePedidoCompra(id);
    }

    @PutMapping(name = "/alteracao-funcionario/${id}")
    public void uptadeFuncionarioDTO(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.update(funcionarioDTO);
    }

}
