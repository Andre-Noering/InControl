package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FuncionarioDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioPayloadDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioVendaDTO;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService funcionarioService;
    @GetMapping
    @RequestMapping("/vendas")
    public FuncionarioVendaDTO getFuncionarioVendaDTO(Long id){
        return funcionarioService.getVendasFuncionario(id);
    }

    @GetMapping
    @RequestMapping("/id_funcionario")
    public FuncionarioDTO get(@PathVariable(name = "id")Long id){
        return funcionarioService.getDTOById(id);
    }

    @PutMapping
    @RequestMapping("/alteracao-funcionario/id")
    public void update(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.update(funcionarioDTO);
    }

    @PostMapping
    @RequestMapping("/adicionarFunc")
    public void save(@RequestBody FuncionarioPayloadDTO funcionarioDTO){
        funcionarioService.save(funcionarioDTO);
    }

}
