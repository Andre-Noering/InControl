package com.entra21.LojaSimulator.view.service;


import com.entra21.LojaSimulator.model.dto.FornecedorContatoDTO;
import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.ItemFornecedorEntity;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import com.entra21.LojaSimulator.view.repository.FornecedorRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;


    @Autowired
    private ItemFornecedorService itemFornecedorService;

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
        FornecedorEntity i = fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!"));
        if (novaRazaoSocial != null) {
            i.setRazao_social(novaRazaoSocial);
        }
        if (novoContato != null) {
            i.setContato(novoContato);
        }
        FornecedorDTO dto = new FornecedorDTO(i.getId(),i.getRazao_social(),i.getCnpj(),i.getContato(),i.getLoja());
        return dto;
    }

    public List<ItemFornecedorEntity> getItensById(Long id) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!"));
        return fornecedor.getItens().stream().map(i -> {
            return itemFornecedorService.getById(i.getItem().getId());
        }).collect(Collectors.toList());
    }

    public List<FornecedorDTO> getAllByLoja(Long idLoja) {
        List<FornecedorEntity> list = fornecedorRepository.findAllByLoja_Id(idLoja);
        return list.stream().map(f -> new FornecedorDTO(f.getId(),f.getRazao_social(),f.getCnpj(),f.getContato(),f.getLoja())).collect(Collectors.toList());
    }

    public String getContatoById(Long id) {
        FornecedorEntity fornecedor = this.getFornecedorById(id);
        String contato = fornecedor.getContato();
        return new FornecedorContatoDTO(contato).getContato();
    }
    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!"));
    }
}
