package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.LojaDTO;
import com.entra21.LojaSimulator.model.dto.LojaUpdateDTO;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${razao_social}")
public class LojaRestController {
    @Autowired
    LojaService lojaService;
    @GetMapping
    public LojaDTO getLoja(@PathVariable String razaoSocial){
        return lojaService.getDTOById(lojaService.getByRazaoSocial(razaoSocial).getId());
    }

    @PutMapping
    public void update(@RequestBody LojaUpdateDTO lojaDTO){
        lojaService.update(lojaDTO);
    }

    @DeleteMapping
    public void delete(@PathVariable String razaoSocial){
        lojaService.deleteByRazaoSocial(razaoSocial);
    }

}
