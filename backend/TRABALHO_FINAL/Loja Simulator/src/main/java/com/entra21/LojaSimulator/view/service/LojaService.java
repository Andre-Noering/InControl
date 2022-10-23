package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.model.entity.*;
import com.entra21.LojaSimulator.view.repository.FornecedorRepository;
import com.entra21.LojaSimulator.view.repository.FuncionarioRepository;
import com.entra21.LojaSimulator.model.entity.LojaEntity;
import com.entra21.LojaSimulator.view.repository.LojaRepository;
import com.entra21.LojaSimulator.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;
    @Autowired
    private ItemFornecedorService itemFornecedorService;
    @Autowired
    private ItemService itemService;


    @Autowired
    PessoaService pessoaService;

    @Autowired
    FornecedorService fornecedorService;

    @Autowired
    FuncionarioService funcionarioService;

    public List<LojaPayloadDTO> getLojasByLogin(String login){
        return lojaRepository.findAllByGerente(funcionarioService.getFuncionarioById(funcionarioService.getIdByLogin(login))).stream().map((loja)-> {
            return new LojaPayloadDTO(loja.getId() ,loja.getRazaoSocial(), loja.getCnpj(), loja.getContato(), loja.getValorCaixa(), loja.getGerente().getId());
        }).collect(Collectors.toList());
    }

    public LojaEntity getById(Long id) {
        return lojaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada!"));
    }


    //GET

    public List<ItemFornecedorDTO> getAllItensFornecedores(String razao_social){
        List<ItemFornecedorDTO> itensFim = new ArrayList<>();
        List<FornecedorEntity> fornecedores = this.getByRazaoSocial(razao_social).getFornecedores();
        List<List<ItemFornecedorDTO>> itensMeio = fornecedores.stream().map(fornecedor -> (fornecedor.getItens().stream().map(item -> itemFornecedorService.getDTOById(item.getId())).collect(Collectors.toList()))
        ).collect(Collectors.toList());
        itensMeio.forEach(itens -> itens.forEach(item -> {if(item.isAtivo()){ itensFim.add(item);}}));
        return itensFim;
    }

    public List<ItemDTO> getItensById(Long id) {
        LojaEntity loja = getById(id);
        return loja.getItens().stream().map(item -> itemService.getDTOById(item.getId())).collect(Collectors.toList());
    }

    public List<FuncionarioPayloadDTO> getFuncionariosByRazaoSocial(String razao_social) {
        LojaEntity loja = getByRazaoSocial(razao_social);
        List<PessoaEntity> listaGeral = loja.getFuncionarios();
        listaGeral.removeIf(pessoa -> !funcionarioService.isFuncionario(pessoa.getId()));
        listaGeral.removeIf(pessoa-> !pessoa.getAtivo());
        return listaGeral.stream().map(func -> funcionarioService.getDTOwithAtivoById(func.getId())).collect(Collectors.toList());
    }

    public List<VendaDTO> getAllVendas(String razaoSocial){
        List<VendaDTO> vendasFim = new ArrayList<>();
        List<PessoaEntity> pessoas = this.getByRazaoSocial(razaoSocial).getFuncionarios();
        pessoas.removeIf(pessoa-> !funcionarioService.isFuncionario(pessoa.getId()));
        List<List<VendaDTO>> vendasMeio = pessoas.stream().map(func -> funcionarioService.getVendasFuncionario(func.getId())
        ).collect(Collectors.toList());
        vendasMeio.forEach(vendas -> vendas.forEach(venda -> vendasFim.add(venda)));
        return vendasFim;
    }
    public List<PedidoCompraDTO> getAllPedidos(String razao_social){
        List<PedidoCompraDTO> pedidosFim=new ArrayList<>();
        List<PessoaEntity> pessoas = this.getByRazaoSocial(razao_social).getFuncionarios();
        pessoas.removeIf(pessoa-> !funcionarioService.isFuncionario(pessoa.getId()));
        List<List<PedidoCompraDTO>> pedidosMeio = pessoas.stream().map(func -> funcionarioService.getPedidosFuncionario(func.getId())
        ).collect(Collectors.toList());
        pedidosMeio.forEach(pedidos -> pedidos.forEach(pedido -> pedidosFim.add(pedido)));
        return pedidosFim;
    }


    public List<FornecedorPayloadDTO> getFornecedoresById(Long id) {
        LojaEntity loja = getById(id);
        return loja.getFornecedores().stream().map(fornecedor -> fornecedorService.getDtoById(fornecedor.getId())).collect(Collectors.toList());
    }

    public LojaGerenteDTO getGerenteById(Long id) {
        LojaEntity loja = getById(id);
        return new LojaGerenteDTO(loja.getGerente());
    }

    public LojaContatoDTO getContatoById(Long id) {
        LojaEntity loja = getById(id);
        return new LojaContatoDTO(loja.getContato());
    }

    public LojaValorCaixaDTO getValorCaixaById(Long id) {
        LojaEntity loja = getById(id);
        return new LojaValorCaixaDTO(loja.getValorCaixa());
    }

    public LojaEntity getByRazaoSocial(String razaoSocial) {
        return lojaRepository.findByRazaoSocial(razaoSocial);
    }

    public LojaPayloadDTO getDTOById(Long id) {
        LojaEntity loja = getById(id);
        return new LojaPayloadDTO(loja.getId(), loja.getRazaoSocial(), loja.getCnpj(), loja.getContato(), loja.getValorCaixa(), loja.getGerente().getId());
    }


    //POST
    public void save(LojaPayloadDTO input) {
        LojaEntity newLoja = new LojaEntity();
        if (!lojaRepository.existsByRazaoSocial(input.getRazao_social())) {
            newLoja.setRazaoSocial(input.getRazao_social());
            newLoja.setCnpj(input.getCnpj());
            newLoja.setContato(input.getContato());
            newLoja.setValorCaixa(input.getValor_caixa());
            newLoja.setGerente(funcionarioService.getFuncionarioById(input.getId_funcionario()));
            lojaRepository.save(newLoja);
        }
    }

    //PUT
    public void update(LojaUpdateDTO dto) {
        LojaEntity loja = getById(dto.getId());
        if (dto.getGerente() != null) {
            loja.setGerente(dto.getGerente());
        }
        if (dto.getContato() != null) {
            loja.setContato(dto.getContato());
        }
        if (dto.getFuncionarios() != null) {
            loja.setFuncionarios(dto.getFuncionarios());
        }
        if (dto.getValorCaixa() != null) {
            loja.setValorCaixa(dto.getValorCaixa());
        }
        if (dto.getItens() != null) {
            loja.setItens(dto.getItens());
        }
        if (dto.getFornecedores() != null) {
            loja.setFornecedores(dto.getFornecedores());
        }
        save(getDTOById(loja.getId()));
    }


    public void delete(Long id) {
        LojaEntity fornecedor = getById(id);
        lojaRepository.delete(fornecedor);
    }

    public void deleteByRazaoSocial(String razaoSocial) {
        lojaRepository.deleteByRazaoSocial(razaoSocial);
    }


}
