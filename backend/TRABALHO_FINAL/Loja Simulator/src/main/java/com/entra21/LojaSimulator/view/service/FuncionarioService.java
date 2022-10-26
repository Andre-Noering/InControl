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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private LojaService lojaService;
    @Autowired PedidoCompraService pedidoCompraService;


    //GET
    public FuncionarioEntity getFuncionarioById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Funcionario não encontrada!"));
    }

    public boolean isFuncionario(Long id){
        return funcionarioRepository.existsById(id);
    }
    public Long getIdByLogin(String login){
        return funcionarioRepository.findByLogin(login).getId();
    }

    //Método Get - Retornando o funcionário pelo ID
    public FuncionarioDTO getDTOById(Long id) {
        FuncionarioEntity funcionario = getFuncionarioById(id);
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setIdPessoa(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setLogin(funcionario.getLogin());
        dto.setSenha(funcionario.getSenha());
        dto.setCpf(funcionario.getCpf());
        dto.setAtivo(funcionario.getAtivo());
        dto.setSobrenome(funcionario.getSobrenome());
        dto.setTelefone(funcionario.getTelefone());
        if(funcionario.getLoja()!=null){
        dto.setIdLoja(funcionario.getLoja().getId());
        }
        return dto;
    }
    public FuncionarioPayloadDTO getDTOwithAtivoById(Long id) {

        FuncionarioEntity funcionario = getFuncionarioById(id);
        FuncionarioPayloadDTO dto = new FuncionarioPayloadDTO();
        dto.setIdPessoa(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setAtivo(funcionario.getAtivo());
        dto.setLogin(funcionario.getLogin());
        dto.setSenha(funcionario.getSenha());
        dto.setCpf(funcionario.getCpf());
        dto.setSobrenome(funcionario.getSobrenome());
        dto.setTelefone(funcionario.getTelefone());
        return dto;
    }

    //Método Get - Retornando todas as vendas do funcionário filtrando o funcionário pelo iD
    public List<VendaDTO> getVendasFuncionario(Long id) {
        FuncionarioEntity funcionario = getFuncionarioById(id);
        return funcionario.getVendas().stream().map(venda -> new VendaDTO(venda.getId(), venda.getData(), venda.getPessoa().getId(), venda.getFuncionario().getId(), venda.getPessoa().getNome()+" "+venda.getPessoa().getSobrenome(), venda.getFuncionario().getNome(), vendaService.getValorTotal(venda.getId()))).collect(Collectors.toList());
    }

    public List<PedidoCompraDTO> getPedidosFuncionario(Long id) {
        FuncionarioEntity funcionario = getFuncionarioById(id);
        return funcionario.getPedidos().stream().map(pedido -> new PedidoCompraDTO(pedido.getId(), pedido.getData(), pedido.getFuncionario().getId(), pedido.getFuncionario().getNome(), pedidoCompraService.getValorTotal(pedido.getId()))).collect(Collectors.toList());
    }


    //Build do funcionário
    public FuncionarioEntity build(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity newFuncionario = new FuncionarioEntity();
        newFuncionario.setId(funcionarioDTO.getIdPessoa());
        newFuncionario.setAtivo(funcionarioDTO.isAtivo());
        newFuncionario.setNome(funcionarioDTO.getNome());
        newFuncionario.setSobrenome(funcionarioDTO.getSobrenome());
        newFuncionario.setTelefone(funcionarioDTO.getTelefone());
        newFuncionario.setCpf(funcionarioDTO.getCpf());
        newFuncionario.setLogin(funcionarioDTO.getLogin());
        newFuncionario.setSenha(funcionarioDTO.getSenha());
        newFuncionario.setLoja(lojaService.getById(funcionarioDTO.getIdLoja()));
        return newFuncionario;
    }


    //Metodo Save - Criando um funcionario
    public void save(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setId(funcionarioDTO.getIdPessoa());
        funcionarioEntity.setNome(funcionarioDTO.getNome());
        funcionarioEntity.setSobrenome(funcionarioDTO.getSobrenome());
        funcionarioEntity.setTelefone(funcionarioDTO.getTelefone());
        funcionarioEntity.setCpf(funcionarioDTO.getCpf());
        funcionarioEntity.setLogin(funcionarioDTO.getLogin());
        funcionarioEntity.setSenha(funcionarioDTO.getSenha());
        if(funcionarioDTO.getIdLoja()!=null) {
            funcionarioEntity.setLoja(lojaService.getById(funcionarioDTO.getIdLoja()));
        }
        funcionarioRepository.save(funcionarioEntity);
    }


    //PUT
    public void update(FuncionarioDTO funcionarioDTO){
        FuncionarioEntity funcionarioEntity = getFuncionarioById(funcionarioDTO.getIdPessoa());
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
            if (funcionarioDTO.getLogin()!=null){
                funcionarioEntity.setLogin(funcionarioEntity.getLogin());
            }
            if(funcionarioDTO.getSenha()!=null){
                funcionarioEntity.setSenha(funcionarioDTO.getSenha());
            }
            funcionarioRepository.save(funcionarioEntity);
    }

    public void delete(Long id){
        FuncionarioEntity f = getFuncionarioById(id);
        f.setAtivo(false);
        f.setLogin("");
        funcionarioRepository.save(f);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FuncionarioEntity en = funcionarioRepository.findByLogin(username);
        if (en == null) {
            throw new UsernameNotFoundException(username);
        }
        return en;
    }

    public FuncionarioDTO login(String login, String password){
        FuncionarioEntity en = funcionarioRepository.findByLogin(login);
        if(en != null && en.getSenha().equals(password)){
            return new FuncionarioDTO(en.getId(),login, password);
        }
        return null;
    }
}
