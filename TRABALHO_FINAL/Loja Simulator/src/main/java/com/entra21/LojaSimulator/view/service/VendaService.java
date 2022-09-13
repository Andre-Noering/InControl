package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.*;
import com.entra21.LojaSimulator.model.entity.*;
import com.entra21.LojaSimulator.view.repository.FuncionarioRepository;
import com.entra21.LojaSimulator.view.repository.PessoaRepository;
import com.entra21.LojaSimulator.view.repository.VendaRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ItemService itemService;
    @Autowired
    private PessoaRepository pessoaRepository;
    public VendaEntity getVenda(Long id){ //Retorna a loja com aquele ID
        return vendaRepository.findById(id).orElseThrow();
    }
    public List<ItemPayloadDTO> getItens(Long id){ //Retorna nome, valor e qtde de cada item naquela venda
        List<ItemVendaEntity> i= getVenda(id).getItens();
        List<ItemPayloadDTO> listaItens = null;
        for(ItemVendaEntity f:i){
            ItemPayloadDTO a = new ItemPayloadDTO();
            a.setNome(f.getItem().getNome());
            a.setValor(f.getValor_unitario());
            a.setQtde(f.getQtde());
            listaItens.add(a);
        }
        return listaItens;
    }

    public PessoaPayloadDTO getVendedor(Long id){ //Retorna o vendedor que efetuou a venda
        VendaEntity v = getVenda(id);
        PessoaEntity f = pessoaRepository.findById(v.getFuncionario().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado!"));
        PessoaPayloadDTO p = new PessoaPayloadDTO();
        p.setNome(f.getNome());
        p.setSobrenome(f.getSobrenome());
        return p;
    }

    public Double getValorTotal(Long id){
        VendaEntity v = getVenda(id);
        Double valorTotal= 0.0;
        for(ItemVendaEntity i:v.getItens()){
            valorTotal+=i.getValor_unitario()*i.getQtde();
        }
        return valorTotal;
    }
    public PessoaPayloadDTO getCliente(Long id){
        VendaEntity v = getVenda(id);
        PessoaEntity p = v.getPessoa();
        PessoaPayloadDTO pessoa = new PessoaPayloadDTO();
        pessoa.setNome(p.getNome());
        pessoa.setSobrenome(p.getSobrenome());
        pessoa.setCpf(p.getCpf());
        pessoa.setTelefone(p.getTelefone());
        return pessoa;
    }
    public void createVenda(VendaPayloadDTO vendaDTO){
        VendaEntity venda = new VendaEntity();
        venda.setData(vendaDTO.getData());
        venda.setPessoa(pessoaRepository.findById(vendaDTO.getId_cliente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!")));
        venda.setFuncionario(funcionarioRepository.findById(vendaDTO.getId_vendedor()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado!")));
    }
    public void addItemVenda(@RequestBody ItemVendaDTO itemVendaDTO){ //Adiciona um item na lista de itens daquela venda
        VendaEntity v = getVenda(itemVendaDTO.getId_venda());
        ItemVendaEntity i = new ItemVendaEntity();
        i.setId(itemVendaDTO.getId());
        i.setVenda(v);
        i.setQtde(itemVendaDTO.getQtde());
        i.setItem(itemService.getItemById(itemVendaDTO.getId_item()));
        v.getItens().add(i);
    }

    public void updateQtde(Long id_item, Long id_venda, int qtde_nova){ //Muda a quantidade do item (id_item) na venda (id_venda) para (qtde_nova)
        VendaEntity v = getVenda(id_venda);
        v.getItens().stream().filter(i -> i.getId()==id_item).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!")).setQtde(qtde_nova);
    }
    public void deleteItemVenda(Long id_item, Long id_venda){ //Remove o item com o id(id_item) da venda com o id(id_venda)
        VendaEntity v = getVenda(id_venda);
        v.getItens().removeIf( i -> i.getId()==id_item);
    }
}
