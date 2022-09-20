package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.view.service.FornecedorService;
import com.entra21.LojaSimulator.view.service.ItemFornecedorService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(name = "/${razao_social}/fornecedores")
public class FornecedorRestController {

    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private LojaService lojaService;
    @GetMapping
    public List<FornecedorDTO> getFornecedores(@PathVariable String razao_social){
        return fornecedorService.getAllByLoja(razao_social);
    }
    @GetMapping(name="/${razao_social}/itens")
    public List<ItemDTO> getItens(String razao_social){
        return fornecedorService.getItensByRazaoSocial(razao_social);
    }
    @GetMapping(name="/${razao_social}/contato")
    public String getContato(String razao_social){
        return fornecedorService.getContatoByRazaoSocial(razao_social);
    }
    @DeleteMapping(name="/${razao_social}")
    public void delete(@PathVariable String razao_social){
        fornecedorService.deleteByRazaoSocial(razao_social);
    }

    @PostMapping(name="/adicionar")
    public void post(FornecedorDTO fornecedorDTO){
        fornecedorService.save(fornecedorDTO);
    }
    @PutMapping(name="/${razao_social}")
    public void update(@RequestBody FornecedorDTO fornecedorDTO){
        fornecedorService.update(fornecedorDTO);
    }


}
