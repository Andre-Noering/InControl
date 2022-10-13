package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.LojaDTO;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerente")
public class GerenteRestController {
    @Autowired
    private LojaService lojaService;
    @PostMapping("/criarLoja")
    public void save(@RequestBody LojaDTO lojaDTO){
        lojaService.save(lojaDTO);
    }
}