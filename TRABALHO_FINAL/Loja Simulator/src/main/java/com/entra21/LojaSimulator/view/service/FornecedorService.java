package com.entra21.LojaSimulator.view.service;


import com.entra21.LojaSimulator.model.dto.FornecedorContatoDTO;
import com.entra21.LojaSimulator.model.dto.FornecedorDTO;
import com.entra21.LojaSimulator.model.dto.ItemDTO;
import com.entra21.LojaSimulator.model.entity.*;
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

    public FornecedorEntity getFornecedorById(Long id){
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Fornecedor não encontrada!"));
    }
    public void save(FornecedorDTO input) {
        FornecedorEntity newFornecedor = new FornecedorEntity();
        newFornecedor.setId(input.getId());
        newFornecedor.setRazao_social(input.getRazao_social());
        newFornecedor.setCnpj(input.getCnpj());
        newFornecedor.setContato(input.getContato());
        newFornecedor.setLoja(input.getLoja());
        fornecedorRepository.save(newFornecedor);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public void update(Long id, String novaRazaoSocial, String novoContato) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        if (novaRazaoSocial != null) {
            fornecedor.setRazao_social(novaRazaoSocial);
        }
        if (novoContato != null) {
            fornecedor.setContato(novoContato);
        }
    }

    public List<ItemFornecedorEntity> getItensById(Long id) {
        FornecedorEntity fornecedor = getFornecedorById(id);
        return fornecedor.getItens().stream().map(item -> itemFornecedorService.getItemFornecedorById(item.getItem().getId())).collect(Collectors.toList());
    }

    public List<FornecedorDTO> getAllByLoja(Long idLoja) {
        List<FornecedorEntity> listaFornecedores = fornecedorRepository.findAllByLoja_Id(idLoja);
        return listaFornecedores.stream().map(fornecedor -> new FornecedorDTO(fornecedor.getId(),fornecedor.getRazao_social(),fornecedor.getCnpj(),fornecedor.getContato(),fornecedor.getLoja())).collect(Collectors.toList());
    }

    public String getContatoById(Long id) {
        FornecedorEntity fornecedor = this.getFornecedorById(id);
        String contato = fornecedor.getContato();
        return new FornecedorContatoDTO(contato).getContato();
    }

}
