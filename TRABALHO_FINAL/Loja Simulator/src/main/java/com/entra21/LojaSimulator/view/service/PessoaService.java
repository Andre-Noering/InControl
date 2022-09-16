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

    public PessoaEntity getPessoaById(Long id){
        return pessoaRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado!"));
    }

    public List<PessoaDTO> getAll() {
        return pessoaRepository.findAll().stream().map(fr -> {
            PessoaDTO dto = new PessoaDTO();
            dto.setIdPessoa(fr.getId());
            dto.setNome(fr.getNome());
            dto.setSobrenome(fr.getSobrenome());
            dto.setTelefone(fr.getTelefone());
            dto.setCpf(fr.getCpf());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(@RequestBody PessoaDTO input) {
        PessoaEntity newEntity = new PessoaEntity();
        newEntity.setId(input.getIdPessoa());
        newEntity.setNome(input.getNome());
        newEntity.setSobrenome(input.getSobrenome());
        newEntity.setTelefone(input.getTelefone());
        newEntity.setCpf(input.getCpf());;
        pessoaRepository.save(newEntity);
    }
    public PessoaEntity build(PessoaDTO input){
        PessoaEntity newEntity = new PessoaEntity();
        newEntity.setId(input.getIdPessoa());
        newEntity.setNome(input.getNome());
        newEntity.setSobrenome(input.getSobrenome());
        newEntity.setTelefone(input.getTelefone());
        newEntity.setCpf(input.getCpf());
        return newEntity;
    }


    //Retorna Pessoa pela id
    public PessoaDTO getById(Long id) {
        PessoaEntity e = getPessoaById(id);
        PessoaDTO dto = new PessoaDTO();
        dto.setIdPessoa(e.getId());
        dto.setNome(e.getNome());
        return dto;
    }


    public void delete(Long id) {
        PessoaEntity pessoa = getPessoaById(id);
        pessoaRepository.delete(pessoa);
    }

    public PessoaDTO update(Long id, String novoNome) {
        PessoaEntity e = getPessoaById(id);
        e.setNome(novoNome);
        e = pessoaRepository.save(e);
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(e.getNome());
        dto.setIdPessoa(e.getId());
        return dto;
    }
}

