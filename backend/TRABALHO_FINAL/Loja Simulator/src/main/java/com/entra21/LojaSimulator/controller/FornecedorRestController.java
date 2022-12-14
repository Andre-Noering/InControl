package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.FornecedorPayloadDTO;
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
@RequestMapping( "/{razao_social}/fornecedores")
public class FornecedorRestController {

    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private LojaService lojaService;

    @GetMapping
    public List<FornecedorPayloadDTO> getFornecedores(@PathVariable String razao_social){
        return fornecedorService.getAllByLoja(razao_social);
    }

    @GetMapping("/{razao_social_fornecedor}/itens")
    public List<ItemDTO> getItens(@PathVariable String razao_social_fornecedor){
        return fornecedorService.getItensByRazaoSocial(razao_social_fornecedor);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        fornecedorService.delete(id);
    }

    @PostMapping("/adicionar")
    public void post(@RequestBody FornecedorPayloadDTO fornecedorDTO){
        fornecedorService.save(fornecedorDTO);
    }

    @PutMapping("/atualizar")
    public void update(@RequestBody FornecedorPayloadDTO fornecedorDTO){
        fornecedorService.update(fornecedorDTO);
    }
}