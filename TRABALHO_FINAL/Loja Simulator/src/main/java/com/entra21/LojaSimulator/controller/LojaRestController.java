package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.LojaDTO;
import com.entra21.LojaSimulator.model.dto.LojaUpdateDTO;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loja")
public class LojaRestController {
    @Autowired
    LojaService lojaService;
    @GetMapping
    @RequestMapping("/{razaoSocial}")
    public LojaDTO getLoja(@PathVariable String razaoSocial){
        return lojaService.getDTOById(lojaService.getByRazaoSocial(razaoSocial).getId());
    }

    @PutMapping
    @RequestMapping("")
    public void update(@RequestBody LojaUpdateDTO lojaDTO){
        lojaService.update(lojaDTO);
    }

    @DeleteMapping
    @RequestMapping("/{razaoSocial}/deletar")
    public void delete(@PathVariable String razaoSocial){
        lojaService.deleteByRazaoSocial(razaoSocial);
    }
}
