package com.entra21.LojaSimulator.view.service;


import com.entra21.LojaSimulator.model.dto.*;
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


    //GET
    public FornecedorPayloadDTO getDtoById(Long id) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        return new FornecedorPayloadDTO(fornecedor.getId(),fornecedor.getRazaoSocial(), fornecedor.getCnpj(), fornecedor.getContato(), fornecedor.getLoja().getId(), fornecedor.getAtivo());
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

    public List<FornecedorPayloadDTO> getAllByLoja(String razao_social) {
        List<FornecedorEntity> listaFornecedores = fornecedorRepository.findAllByLoja_RazaoSocial(razao_social);
        listaFornecedores.removeIf(fornecedor-> !fornecedor.getAtivo());
        return listaFornecedores.stream().map(fornecedor -> new FornecedorPayloadDTO(fornecedor.getId(),fornecedor.getRazaoSocial(),fornecedor.getCnpj(),fornecedor.getContato(),fornecedor.getLoja().getId(), fornecedor.getAtivo())).collect(Collectors.toList());
    }

    public String getContatoByRazaoSocial(String razao_social) {
        return this.getFornecedorByRazaoSocial(razao_social).getContato();
    }

    public FornecedorEntity getFornecedorByRazaoSocial(String razao_social){
        return fornecedorRepository.findByRazaoSocial(razao_social);
    }

    public FornecedorDTO getDTOById(Long id){
        FornecedorEntity fornecedor = getFornecedorById(id);
        return new FornecedorDTO(fornecedor.getId(), fornecedor.getRazaoSocial(), fornecedor.getCnpj(), fornecedor.getContato(), fornecedor.getLoja());
    }
    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Fornecedor não encontrada!"));
    }


    //POST
    public void save(FornecedorPayloadDTO input) {
        FornecedorEntity newFornecedor = new FornecedorEntity();
        newFornecedor.setId(input.getId());
        newFornecedor.setRazaoSocial(input.getRazao_social());
        newFornecedor.setCnpj(input.getCnpj());
        newFornecedor.setContato(input.getContato());
        newFornecedor.setLoja(lojaService.getById(input.getIdLoja()));
        fornecedorRepository.save(newFornecedor);
    }


    //PUT
    public void update(FornecedorPayloadDTO fornecedorDTO) {
        FornecedorEntity fornecedor = getFornecedorById(fornecedorDTO.getId());
        if (fornecedorDTO.getRazao_social() != null) {
            fornecedor.setRazaoSocial(fornecedorDTO.getRazao_social());
        }
        if (fornecedorDTO.getCnpj()!=null){
            fornecedor.setCnpj(fornecedorDTO.getCnpj());
        }
        if (fornecedorDTO.getContato() != null) {
            fornecedor.setContato(fornecedorDTO.getContato());
        }
        fornecedorRepository.save(fornecedor);
    }


    //DELETE
    public void deleteByRazaoSocial(String razaoSocial){
        FornecedorEntity f= this.getFornecedorByRazaoSocial(razaoSocial);
        f.setAtivo(false);
        fornecedorRepository.save(f);
    }
    public void delete(Long id) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        fornecedor.setAtivo(false);
        fornecedorRepository.save(fornecedor);
    }
}
