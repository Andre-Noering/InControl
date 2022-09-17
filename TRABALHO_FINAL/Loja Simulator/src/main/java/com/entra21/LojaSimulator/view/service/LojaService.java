package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import com.entra21.LojaSimulator.model.entity.PessoaEntity;
import com.entra21.LojaSimulator.view.repository.FornecedorRepository;
import com.entra21.LojaSimulator.view.repository.FuncionarioRepository;
import com.entra21.LojaSimulator.model.dto.LojaDTO;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import com.entra21.LojaSimulator.view.repository.LojaRepository;
import com.entra21.LojaSimulator.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    public LojaEntity getById(Long id){
        return lojaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Loja não encontrada!"));
    }
}
