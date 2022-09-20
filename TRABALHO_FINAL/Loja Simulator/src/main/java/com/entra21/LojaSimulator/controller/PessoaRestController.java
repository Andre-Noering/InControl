package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.PessoaDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaDTO> getPessoa() {
        return PessoaService.getAll();
    }

    @PostMapping
    public void addPessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        pessoaService.save(new PessoaDTO());
    }

    @GetMapping("/")
    public PessoaDTO getFranquia(@PathVariable(name = "id") Long id) {
        return pessoaService.getDTOById(id);
    }

    @DeleteMapping("/")
    public void deletePessoa(@PathVariable(name = "id") Long id) {
        PessoaService.delete(id);
    }

    @PutMapping("/")
    public PessoaDTO updatePessoa(@PathVariable(name = "id") Long id,
                                  @RequestBody String novoNome) {
        return pessoaService.update(id, novoNome);
    }
}