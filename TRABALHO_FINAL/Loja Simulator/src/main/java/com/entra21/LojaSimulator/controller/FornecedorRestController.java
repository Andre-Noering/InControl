package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.view.service.FornecedorService;
import com.entra21.LojaSimulator.view.service.ItemFornecedorService;
import com.entra21.LojaSimulator.view.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorRestController {

    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private LojaService lojaService;
    @GetMapping
    @RequestMapping("/{razao_social}")
    public List<FornecedorDTO> getFornecedores(@PathVariable(name = "razao_social") String razao_social){
        return fornecedorService.getAllByLoja(razao_social);
    }
    @GetMapping
    @RequestMapping("/{razao_social}/itens")
    public List<ItemDTO> getItens(@PathVariable String razao_social){
        return fornecedorService.getItensByRazaoSocial(razao_social);
    }
    @GetMapping
    @RequestMapping("/{razao_social}/contato")
    public String getContato(@PathVariable String razao_social){
        return fornecedorService.getContatoByRazaoSocial(razao_social);
    }
    @DeleteMapping
    @RequestMapping("/{id}/deletar")
    public void delete(@PathVariable Long id){
        fornecedorService.delete(id);
    }

    @PostMapping
    @RequestMapping("/adicionar")
    public void post(@RequestBody FornecedorDTO fornecedorDTO){
        fornecedorService.save(fornecedorDTO);
    }

    @PutMapping
    @RequestMapping("/atualizar")
    public void update(@RequestBody FornecedorDTO fornecedorDTO){
        fornecedorService.update(fornecedorDTO);
    }
}