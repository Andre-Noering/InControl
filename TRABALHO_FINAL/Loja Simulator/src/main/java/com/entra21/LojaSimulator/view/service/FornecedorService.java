package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import com.entra21.LojaSimulator.view.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void save(FornecedorDTO input) {
        FornecedorEntity newEntity = new FornecedorEntity();
        newEntity.setId(input.getId());
        newEntity.setRazao_social(input.getRazao_social());
        newEntity.setCnpj(input.getCnpj());
        newEntity.setContato(input.getContato());
        newEntity.setLoja(input.getLoja());
        fornecedorRepository.save(newEntity);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public FornecedorDTO update(Long id, String novaRazaoSocial, String novoContato) {
        FornecedorEntity i = fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        if (novaRazaoSocial != null) {
            i.setRazao_social(novaRazaoSocial);
        }
        if (novoContato != null) {
            i.setContato(novoContato);
        }
        FornecedorDTO dto = new FornecedorDTO(i.getId(),i.getRazao_social(),i.getCnpj(),i.getContato(),i.getLoja());
        return dto;
    }

}
