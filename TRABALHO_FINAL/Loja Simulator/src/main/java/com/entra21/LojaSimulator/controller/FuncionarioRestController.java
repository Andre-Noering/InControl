package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FuncionarioDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioPayloadDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioVendaDTO;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{razaoSocial}/funcionario")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService funcionarioService;
    @GetMapping("/vendas")
    public FuncionarioVendaDTO getFuncionarioVendaDTO(Long id){
        return funcionarioService.getVendasFuncionario(id);
    }

    @GetMapping("/{id_funcionario}")
    public FuncionarioDTO get(@PathVariable Long id_funcionario){
        return funcionarioService.getDTOById(id_funcionario);
    }

    @PutMapping("/atualizar")
    public void update(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.update(funcionarioDTO);
    }

    @PostMapping("/adicionar")
    public void save(@RequestBody FuncionarioPayloadDTO funcionarioDTO){
        funcionarioService.save(funcionarioDTO);
    }


}
