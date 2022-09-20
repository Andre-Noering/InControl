package com.entra21.LojaSimulator.view.service;


import com.entra21.LojaSimulator.model.dto.FornecedorContatoDTO;
import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.dto.ItemFornecedorDTO;
import com.entra21.LojaSimulator.model.entity.*;
import com.entra21.LojaSimulator.view.repository.FornecedorRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private LojaService lojaService;

    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Fornecedor não encontrada!"));
    }
    public void save(@RequestBody FornecedorDTO input) {
        FornecedorEntity newFornecedor = new FornecedorEntity();
        newFornecedor.setId(input.getId());
        newFornecedor.setRazao_social(input.getRazao_social());
        newFornecedor.setCnpj(input.getCnpj());
        newFornecedor.setContato(input.getContato());
        newFornecedor.setLoja(input.getLoja());
        fornecedorRepository.save(newFornecedor);
    }

    public void delete(Long id) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        fornecedorRepository.delete(fornecedor);
    }


    public void update(FornecedorDTO fornecedorDTO) {
        FornecedorEntity fornecedor = getFornecedorById(fornecedorDTO.getId());
        if (fornecedorDTO.getRazao_social() != null) {
            fornecedor.setRazao_social(fornecedorDTO.getRazao_social());
        }
        if (fornecedorDTO.getContato() != null) {
            fornecedor.setContato(fornecedorDTO.getContato());
        }
        save(getDtoById(fornecedor.getId()));
    }

    public FornecedorDTO getDtoById(Long id) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        return new FornecedorDTO(fornecedor.getId(),fornecedor.getRazao_social(), fornecedor.getCnpj(), fornecedor.getContato(), fornecedor.getLoja());
    }

    public List<ItemFornecedorEntity> getItensById(Long id) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        return fornecedor.getItens().stream().map(item -> itemFornecedorService.getItemFornecedorById(item.getItem().getId())).collect(Collectors.toList());
    }
    public List<ItemDTO> getItensByRazaoSocial(String razao_social){
        return getFornecedorByRazaoSocial(razao_social).getItens().stream().map(item -> {
            return itemFornecedorService.getItemDTO(item.getItem().getId());
        }).collect(Collectors.toList());
    }

    public List<FornecedorDTO> getAllByLoja(String razao_social) {
        List<FornecedorEntity> listaFornecedores = fornecedorRepository.findAllByLoja_Razao_Social(razao_social);
        return listaFornecedores.stream().map(fornecedor -> new FornecedorDTO(fornecedor.getId(),fornecedor.getRazao_social(),fornecedor.getCnpj(),fornecedor.getContato(),fornecedor.getLoja())).collect(Collectors.toList());
    }

    public String getContatoByRazaoSocial(String razao_social) {
        return this.getFornecedorByRazaoSocial(razao_social).getContato();
    }

    public FornecedorEntity getFornecedorByRazaoSocial(String razao_social){
        return fornecedorRepository.findByRazao_Social(razao_social);
    }

    public FornecedorDTO getDTOById(Long id){
        FornecedorEntity fornecedor = getFornecedorById(id);
        return new FornecedorDTO(fornecedor.getId(), fornecedor.getRazao_social(), fornecedor.getCnpj(), fornecedor.getContato(), fornecedor.getLoja());
    }

    public void deleteByRazaoSocial(String razao_social){
        fornecedorRepository.deleteByRazao_Social(razao_social);
    }
}
