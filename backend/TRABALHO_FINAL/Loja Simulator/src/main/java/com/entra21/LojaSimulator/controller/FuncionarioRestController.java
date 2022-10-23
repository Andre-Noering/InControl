package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private LojaService lojaService;


    @GetMapping("/{razao_social}")
    public List<FuncionarioPayloadDTO> getFuncionarios(@PathVariable String razao_social){
        return lojaService.getFuncionariosByRazaoSocial(razao_social);
    }
    @GetMapping("/vendas/{id}")
    public List<VendaDTO> getFuncionarioVendaDTO(@PathVariable Long id){
        return funcionarioService.getVendasFuncionario(id);
    }

    @GetMapping("/pedidos/{id}")
    public List<PedidoCompraDTO> getFuncionarioPedidoDTO(@PathVariable Long id){
        return funcionarioService.getPedidosFuncionario(id);
    }

    @GetMapping("/funcionario/{login}")
    public FuncionarioDTO getFuncionarioDTOByLogin(@PathVariable String login){
        return funcionarioService.getDTOById(funcionarioService.getIdByLogin(login));
    }
    @GetMapping("/funcionario/{id}/")
    public FuncionarioDTO getFuncionarioDTOById(@PathVariable Long id){
        return funcionarioService.getDTOById(id);
    }

    @PutMapping("/alteracao-funcionario/{id}")
    public void updateFuncionarioDTO(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.update(funcionarioDTO);
    }

    @PostMapping("/adicionar")
    public void saveFuncionarioDTO(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.save(funcionarioDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFuncionario(@PathVariable Long id){
        funcionarioService.delete(id);
    }

}
