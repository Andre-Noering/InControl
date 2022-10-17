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

    @Autowired
    private LojaService lojaService;

    //GET
    public FuncionarioEntity getById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Funcionário não encontrado!"));
    }

    //Método Get - Retornando o funcionário pelo ID
    public FuncionarioDTO getDTOById(Long id) {
        FuncionarioEntity funcionario = getById(id);
        if (!funcionario.getAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Funcionário está inativo");
        } else {
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
    }

    //Método Get - Retornando todas as vendas do funcionário filtrando o funcionário pelo iD
    public FuncionarioVendaDTO getVendasFuncionario(Long id) {
        Optional<FuncionarioEntity> funcionario = funcionarioRepository.findById(id);
        if (!funcionario.get().getAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Funcionário está inativo");
        } else
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
    public void save(FuncionarioPayloadDTO funcionarioDTO){
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setNome(funcionarioDTO.getNome());
        funcionarioEntity.setSobrenome(funcionarioDTO.getSobrenome());
        funcionarioEntity.setTelefone(funcionarioDTO.getTelefone());
        funcionarioEntity.setCpf(funcionarioDTO.getCpf());
        funcionarioEntity.setLogin(funcionarioDTO.getLogin());
        funcionarioEntity.setSenha(funcionarioDTO.getSenha());
        funcionarioEntity.setLoja(lojaService.getById(funcionarioDTO.getIdLoja()));
        funcionarioRepository.save(funcionarioEntity);
    }


    //PUT
    public void update(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity funcionarioEntity = getById(funcionarioDTO.getId());
        if (!funcionarioEntity.getAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário está inativo");
        } else {
            if (funcionarioDTO.getCpf() != null) {
                funcionarioEntity.setCpf(funcionarioDTO.getCpf());
            }
            if (funcionarioDTO.getNome() != null) {
                funcionarioEntity.setNome(funcionarioDTO.getNome());
            }
            if (funcionarioDTO.getSobrenome() != null) {
                funcionarioEntity.setSobrenome(funcionarioDTO.getSobrenome());
            }
            if (funcionarioDTO.getTelefone() != null) {
                funcionarioEntity.setTelefone(funcionarioDTO.getTelefone());
            }
            if (funcionarioDTO.getSenha() != null) {
                funcionarioEntity.setSenha(funcionarioDTO.getSenha());
            }
            if (funcionarioDTO.getLogin() != null && !funcionarioRepository.existsByLogin(funcionarioDTO.getLogin())) {
                funcionarioEntity.setLogin(funcionarioDTO.getLogin());
            }
            funcionarioRepository.save(funcionarioEntity);
        }
    }

    public void delete(Long id) {
        if (getById(id).getAtivo()) {
            getById(id).setAtivo(false);
            funcionarioRepository.save(getById(id));
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Funcionário já está inativo");
    }

    public void ativar(Long id) {
        if (!getById(id).getAtivo()) {
            getById(id).setAtivo(true);
            funcionarioRepository.save(getById(id));
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Funcionário já está ativo");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FuncionarioEntity en = funcionarioRepository.findByLogin(username);
        if (!en.getAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Funcionário está inativo");
        } else {
            if (en == null) {
                throw new UsernameNotFoundException(username);
            }
            return en;
        }
    }



}
