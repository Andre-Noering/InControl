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
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setIdPessoa(fr.getId());
            pessoaDTO.setNome(fr.getNome());
            pessoaDTO.setSobrenome(fr.getSobrenome());
            pessoaDTO.setTelefone(fr.getTelefone());
            pessoaDTO.setCpf(fr.getCpf());
            return pessoaDTO;
        }).collect(Collectors.toList());
    }

    public void save(@RequestBody PessoaDTO pessoaDTO) {
        pessoaRepository.save(build(pessoaDTO));
    }
    public PessoaEntity build(PessoaDTO input){
        PessoaEntity newPessoa = new PessoaEntity();
        newPessoa.setId(input.getIdPessoa());
        newPessoa.setNome(input.getNome());
        newPessoa.setSobrenome(input.getSobrenome());
        newPessoa.setTelefone(input.getTelefone());
        newPessoa.setCpf(input.getCpf());
        return newPessoa;
    }


    //Retorna Pessoa pela id
    public PessoaDTO getDTOById(Long id) {
        PessoaEntity pessoaEntity = getPessoaById(id);
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(pessoaEntity.getId());
        pessoaDTO.setNome(pessoaEntity.getNome());
        return pessoaDTO;
    }


    public void delete(Long id) {
        PessoaEntity pessoa = getPessoaById(id);
        pessoaRepository.delete(pessoa);
    }

    public PessoaDTO update(Long id, String novoNome) {
        PessoaEntity pessoaEntity = getPessoaById(id);
        pessoaEntity.setNome(novoNome);
        pessoaEntity = pessoaRepository.save(pessoaEntity);
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(pessoaEntity.getNome());
        pessoaDTO.setIdPessoa(pessoaEntity.getId());
        return pessoaDTO;
    }
}

