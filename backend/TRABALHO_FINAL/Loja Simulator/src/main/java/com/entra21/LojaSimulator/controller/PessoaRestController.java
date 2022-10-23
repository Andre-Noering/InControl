package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.PessoaDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{razao_social}/pessoas")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/clientes")
    public List<PessoaDTO> getClientes(@PathVariable String razao_social){
        return pessoaService.getClientes(razao_social);
    }

    @GetMapping( "/{id}")
    public PessoaDTO getPessoa(@PathVariable Long id) {
        return pessoaService.getDTOById(id);
    }

    @PostMapping( "/salvar")
    public void savePessoa(@RequestBody PessoaDTO pessoaDTO) {
        pessoaService.save(pessoaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable Long id) {
        pessoaService.delete(id);
    }

    @PutMapping("/update")
    public void updatePessoa(@RequestBody PessoaDTO pessoaDTO){
        pessoaService.update(pessoaDTO);
    }
}