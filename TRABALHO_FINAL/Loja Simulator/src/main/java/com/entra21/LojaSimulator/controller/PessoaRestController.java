package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.PessoaDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @RequestMapping("/idPessoa")
    public PessoaDTO getPessoa(@PathVariable Long id) {
        return pessoaService.getDTOById(id);
    }

    @PostMapping
    @RequestMapping("/adicionar")
    public void savePessoa(@RequestBody PessoaDTO pessoaDTO) {
        pessoaService.save(pessoaDTO);
    }

    @DeleteMapping
    @RequestMapping("/id/deletar")
    public void deletePessoa(@PathVariable Long id) {
        pessoaService.delete(id);
    }

    @PutMapping
    @RequestMapping("/atualizar")
    public void updatePessoa(@RequestBody PessoaDTO pessoaDTO){
        pessoaService.update(pessoaDTO);
    }
}