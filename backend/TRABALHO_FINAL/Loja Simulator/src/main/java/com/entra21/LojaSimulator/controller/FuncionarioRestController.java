package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FuncionarioDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioVendaDTO;
import com.entra21.LojaSimulator.model.dto.VendaDTO;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService funcionarioService;
    @GetMapping("/vendas")
    public List<VendaDTO> getFuncionarioVendaDTO(Long id){
        return funcionarioService.getVendasFuncionario(id);
    }

    @GetMapping("/{id_funcionario}")
    public FuncionarioDTO getFuncionarioDTO(@PathVariable("id_funcionario")Long id){
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

}
