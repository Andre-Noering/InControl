package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FuncionarioDTO;
import com.entra21.LojaSimulator.model.dto.LoginDTO;
import com.entra21.LojaSimulator.model.dto.LojaDTO;
import com.entra21.LojaSimulator.model.dto.LojaPayloadDTO;
import com.entra21.LojaSimulator.view.service.FuncionarioService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class GerenteRestController {
    @Autowired
    private LojaService lojaService;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/{login}/lojas")
    public List<LojaPayloadDTO> getLojasOfGerente(@PathVariable String login){
        return lojaService.getLojasByLogin(login);
    }

    @PostMapping("/adicionarLoja")
    public void save(@RequestBody LojaPayloadDTO lojaDTO){
        lojaService.save(lojaDTO);
    }
    @PostMapping("/funcionario/adicionar")
    public void adicionar(@RequestBody FuncionarioDTO funcionarioDTO){
        funcionarioService.save(funcionarioDTO);
    }

    @PostMapping("/login")
    public FuncionarioDTO login(@RequestBody LoginDTO loginDTO){
        return funcionarioService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }
}