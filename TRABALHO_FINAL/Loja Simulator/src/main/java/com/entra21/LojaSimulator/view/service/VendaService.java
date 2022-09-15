package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemPayloadDTO;
import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.model.dto.VendaPayloadDTO;
import com.entra21.LojaSimulator.model.entity.VendaEntity;
import com.entra21.LojaSimulator.view.repository.VendaRepository;
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
    private FuncionarioService funcionarioService;

    @Autowired
    private ItemService itemService;
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ItemVendaService itemVendaService;
    public VendaEntity getVenda(Long id){ //Retorna a loja com aquele ID
        return vendaRepository.findById(id).orElseThrow();
    }
    public List<ItemPayloadDTO> getItens(Long id){ //Retorna nome, valor e qtde de cada item naquela venda
        List<ItemPayloadDTO> listaItens = null;
        getVenda(id).getItens().forEach( f -> {
            ItemPayloadDTO a = new ItemPayloadDTO();
            a.setNome(f.getItem().getNome());
            a.setValor(f.getValor_unitario());
            a.setQtde(f.getQtde());
            listaItens.add(a);
        });
        return listaItens;
    }

    public PessoaPayloadDTO getVendedor(Long id){ //Retorna o vendedor que efetuou a venda
        VendaEntity v = getVenda(id);
        PessoaPayloadDTO p = new PessoaPayloadDTO();
        p.setNome(funcionarioService.findFuncById(v.getFuncionario().getId()).getNome());
        p.setSobrenome(funcionarioService.findFuncById(v.getFuncionario().getId()).getSobrenome());
        return p;
    }

    public Double getValorTotal(Long id){
        VendaEntity v = getVenda(id);
        Double valorTotal= 0.0;
        v.getItens().forEach(i -> valorTotal+=i.getValor_unitario()*i.getQtde());
        return valorTotal;
    }
    public PessoaPayloadDTO getCliente(Long id){
        VendaEntity v = getVenda(id);
        PessoaPayloadDTO pessoa = new PessoaPayloadDTO();
        pessoa.setNome(v.getPessoa().getNome());
        pessoa.setSobrenome(v.getPessoa().getSobrenome());
        pessoa.setCpf(v.getPessoa().getCpf());
        pessoa.setTelefone(v.getPessoa().getTelefone());
        return pessoa;
    }
    public void createVenda(VendaPayloadDTO vendaDTO){
        VendaEntity venda = new VendaEntity();
        venda.setData(vendaDTO.getData());
        venda.setPessoa(pessoaService.createPessoa(pessoaService.getById(vendaDTO.getId_cliente())));
        venda.setFuncionario(funcionarioService.createFuncionario(findFuncById(vendaDTO.getId_vendedor())));
    }
    public void addItemVenda(@RequestBody ItemVendaDTO itemVendaDTO){ //Adiciona um item na lista de itens daquela venda
        VendaEntity v = getVenda(itemVendaDTO.getId_venda());
        itemVendaService.createItemVenda(itemVendaDTO, v);
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
