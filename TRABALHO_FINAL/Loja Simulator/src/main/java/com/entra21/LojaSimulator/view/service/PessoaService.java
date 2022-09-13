package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.PessoaDTO;
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
        newEntity.setNome(input.getNome());
        pessoaRepository.save(newEntity);
    }

    public PessoaDTO getById(Long id) {
        PessoaEntity e = pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado!"));
        PessoaDTO dto = new PessoaDTO();
        dto.setIdPessoa(e.getId());
        dto.setNome(e.getNome());
        return dto;
    }

    public void delete(Long id) {
        pessoaRepository.deleteAllById(Collections.singleton(id));
    }

    public PessoaDTO update(Long id, String novoNome) {
        PessoaEntity e = pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado!"));
        e.setNome(novoNome);
        e = pessoaRepository.save(e);
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(e.getNome());
        dto.setIdPessoa(e.getId());
        return dto;
    }
}
