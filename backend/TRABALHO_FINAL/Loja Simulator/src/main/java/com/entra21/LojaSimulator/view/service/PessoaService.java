package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.PessoaDTO;
import com.entra21.LojaSimulator.model.entity.PedidoCompraEntity;
import com.entra21.LojaSimulator.model.entity.PessoaEntity;
import com.entra21.LojaSimulator.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private LojaService lojaService;

    public List<PessoaDTO> getClientes(String razao_social){
        List<PessoaEntity> pessoas= lojaService.getByRazaoSocial(razao_social).getFuncionarios();
        pessoas.removeIf(pessoa -> funcionarioService.isFuncionario(pessoa.getId()));
        pessoas.removeIf(pessoa-> !pessoa.getAtivo());
        return pessoas.stream().map(pessoa-> {
            return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getSobrenome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getLoja().getId(), pessoa.getAtivo());
        }).collect(Collectors.toList());
    }


    public PessoaEntity getPessoaById(Long id){
        return pessoaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado!"));
    }

    public List<PessoaDTO> getAll() {
        return pessoaRepository.findAll().stream().map(fr -> {
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setIdPessoa(fr.getId());
            pessoaDTO.setNome(fr.getNome());
            pessoaDTO.setSobrenome(fr.getSobrenome());
            pessoaDTO.setTelefone(fr.getTelefone());
            pessoaDTO.setCpf(fr.getCpf());
            return pessoaDTO;
        }).collect(Collectors.toList());
    }

    public PessoaEntity build(PessoaDTO input){
        PessoaEntity newPessoa = new PessoaEntity();
        newPessoa.setId(input.getIdPessoa());
        newPessoa.setNome(input.getNome());
        newPessoa.setSobrenome(input.getSobrenome());
        newPessoa.setTelefone(input.getTelefone());
        newPessoa.setCpf(input.getCpf());
        newPessoa.setLoja(lojaService.getById(input.getIdLoja()));
        return newPessoa;
    }

    //Retorna Pessoa pela id
    public PessoaDTO getDTOById(Long id) {
        PessoaEntity pessoaEntity = getPessoaById(id);
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(pessoaEntity.getId());
        pessoaDTO.setNome(pessoaEntity.getNome());
        pessoaDTO.setSobrenome(pessoaEntity.getSobrenome());
        pessoaDTO.setCpf(pessoaEntity.getCpf());
        pessoaDTO.setTelefone(pessoaEntity.getTelefone());
        pessoaDTO.setIdLoja(pessoaEntity.getLoja().getId());
        return pessoaDTO;
    }

    //POST
    public void save(@RequestBody PessoaDTO pessoaDTO) {

        pessoaRepository.save(build(pessoaDTO));
    }

    //PUT
    public void update(PessoaDTO pessoaDTO) {
        PessoaEntity pessoaEntity = getPessoaById(pessoaDTO.getIdPessoa());
        pessoaEntity.setNome(pessoaDTO.getNome());
        pessoaEntity.setCpf(pessoaDTO.getCpf());
        pessoaEntity.setSobrenome(pessoaDTO.getSobrenome());
        pessoaEntity.setTelefone(pessoaDTO.getTelefone());
        pessoaRepository.save(pessoaEntity);
    }

    //DELETE
    public void delete(Long id) {
        PessoaEntity pessoa = getPessoaById(id);
        pessoa.setAtivo(false);
        pessoaRepository.save(pessoa);
    }


}

