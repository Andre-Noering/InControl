package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.model.entity.*;
import com.entra21.LojaSimulator.view.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class FuncionarioService implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private VendaService vendaService;


    //GET
    public FuncionarioEntity getFuncionarioById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Funcionario não encontrada!"));
    }

    //Método Get - Retornando o funcionário pelo ID
    public FuncionarioDTO getDTOById(Long id) {
        FuncionarioEntity funcionario = getFuncionarioById(id);
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setLogin(funcionario.getLogin());
        dto.setSenha(funcionario.getSenha());
        dto.setCpf(dto.getCpf());
        dto.setSobrenome(dto.getSobrenome());
        dto.setTelefone(dto.getTelefone());
        return dto;
    }

    //Método Get - Retornando todas as vendas do funcionário filtrando o funcionário pelo iD
    public FuncionarioVendaDTO getVendasFuncionario(Long id) {
        Optional<FuncionarioEntity> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(funcionarioEntity -> new FuncionarioVendaDTO(funcionarioEntity.getVendas())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado!"));
    }


    //Build do funcionário
    public FuncionarioEntity build(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity newFuncionario = new FuncionarioEntity();
        newFuncionario.setId(funcionarioDTO.getId());
        newFuncionario.setNome(funcionarioDTO.getNome());
        newFuncionario.setSobrenome(funcionarioDTO.getSobrenome());
        newFuncionario.setTelefone(funcionarioDTO.getTelefone());
        newFuncionario.setCpf(funcionarioDTO.getCpf());
        newFuncionario.setLogin(funcionarioDTO.getLogin());
        newFuncionario.setSenha(funcionarioDTO.getSenha());
        return newFuncionario;
    }


    //Metodo Save - Criando um funcionario
    public void save(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setId(funcionarioDTO.getId());
        funcionarioEntity.setNome(funcionarioDTO.getNome());
        funcionarioEntity.setSobrenome(funcionarioDTO.getSobrenome());
        funcionarioEntity.setTelefone(funcionarioDTO.getTelefone());
        funcionarioEntity.setCpf(funcionarioDTO.getCpf());
        funcionarioEntity.setLogin(funcionarioDTO.getLogin());
        funcionarioEntity.setSenha(funcionarioDTO.getSenha());
        funcionarioRepository.save(funcionarioEntity);
    }


    //PUT
    public void update(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity funcionarioEntity = getFuncionarioById(funcionarioDTO.getId());
            if (funcionarioDTO.getCpf() != null){
                funcionarioEntity.setCpf(funcionarioDTO.getCpf());
            }
            if (funcionarioDTO.getNome() != null){
                funcionarioEntity.setCpf(funcionarioDTO.getNome());
            }
            if (funcionarioDTO.getSobrenome() != null){
                funcionarioEntity.setCpf(funcionarioDTO.getSobrenome());
            }
            if (funcionarioDTO.getTelefone() != null){
                funcionarioEntity.setCpf(funcionarioDTO.getTelefone());
            }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FuncionarioEntity en = funcionarioRepository.findByLogin(username);
        if (en == null) {
            throw new UsernameNotFoundException(username);
        }
        return en;
    }

}