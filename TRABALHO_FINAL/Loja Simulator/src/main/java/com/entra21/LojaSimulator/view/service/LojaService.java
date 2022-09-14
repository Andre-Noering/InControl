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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;


    //PARAMETRO DE LONG ID É O ID DA LOJA
    public LojaEntity getLoja(Long id) {
        return lojaRepository.findById(id).get();
    }

    public Double getValorCaixa(Long id) { //Retorna o valor de caixa da loja
        return getLoja(id).getValor_caixa();
    }

    public List<FornecedorDTO> getFornecedores(Long id) { //Retorna a lista de fornecedores cadastrados da loja
        List<FornecedorDTO> listaForn = null;
        List<FornecedorEntity> l = fornecedorRepository.findAll().stream().filter(f -> f.getLoja().getId() == id).collect(Collectors.toList());
        for (FornecedorEntity f : l) {
            FornecedorDTO forn = new FornecedorDTO();
            forn.setCnpj(f.getCnpj());
            forn.setContato(f.getContato());
            forn.setId(f.getId());
            forn.setRazao_social(f.getRazao_social());
            listaForn.add(forn);
        }
        return listaForn;
    }

    public List<PessoaPayloadDTO> getFuncionarios(Long id) { //Retorna nome e sobrenome de todos os funcionários cadastrados da loja(PESSOA SERVICE VAI TER O MAIS DETALHADO)
        List<PessoaPayloadDTO> listaNomes = null;
        for(FuncionarioEntity f : getLoja(id).getFuncionarios()){
            PessoaPayloadDTO p = new PessoaPayloadDTO();
            p.setNome(f.getNome());
            p.setSobrenome(f.getSobrenome());
            listaNomes.add(p);
        }
        return listaNomes;
    }
}
