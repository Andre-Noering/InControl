package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FuncionarioDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioPayloadDTO;
import com.entra21.LojaSimulator.model.dto.FuncionarioVendaDTO;
import com.entra21.LojaSimulator.model.dto.VendaDTO;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{razao_social}/funcionarios")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private LojaService lojaService;


    @GetMapping()
    public List<FuncionarioPayloadDTO> getFuncionarios(@PathVariable String razao_social){
        return lojaService.getFuncionariosByRazaoSocial(razao_social);
    }
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
