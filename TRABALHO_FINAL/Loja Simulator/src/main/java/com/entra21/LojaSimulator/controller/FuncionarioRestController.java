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
        return funcionarioService.getDTOById(id);
    }

    @PutMapping(name = "/alteracao-funcionario/${id}")
    public void updateFuncionarioDTO(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.update(funcionarioDTO);
    }

    @PostMapping(name = "/adicionar")
    public void saveFuncionarioDTO(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.save(funcionarioDTO);
    }

}
