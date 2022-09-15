package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemPayloadDTO;
import com.entra21.LojaSimulator.model.dto.ItemVendaDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.model.dto.VendaPayloadDTO;
import com.entra21.LojaSimulator.model.entity.VendaEntity;
import com.entra21.LojaSimulator.view.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public void save(VendaPayloadDTO vendaDTO){
        VendaEntity venda = new VendaEntity();
        venda.setData(vendaDTO.getData());
        venda.setPessoa(pessoaService.createPessoa(pessoaService.findPessoaById(vendaDTO.getId_cliente())));
        venda.setFuncionario(funcionarioService.createFuncionario(funcionarioService.findFuncById(vendaDTO.getId_vendedor())));
        vendaRepository.save(venda);
    }

    public void delete(Long id){
        vendaRepository.deleteById(id);
    }

    //Retorna nome, valor e qtde de cada item naquela venda
    public List<ItemPayloadDTO> getItens(Long id){
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

    //Retorna o vendedor que efetuou a venda
    public PessoaPayloadDTO getVendedor(Long id){
        VendaEntity v = getVenda(id);
        PessoaPayloadDTO p = new PessoaPayloadDTO();
        p.setNome(funcionarioService.createFuncionario(funcionarioService.findFuncById(v.getFuncionario().getId()).getNome());
        p.setSobrenome(funcionarioService.createFuncionario(funcionarioService.findFuncById(v.getFuncionario().getId())).getSobrenome());
        return p;
    }

    //Retorna o valor total da Venda
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
//        i.setItem(itemService.getItemById(itemVendaDTO.getId_item())); Método do Item Venda
        v.getItens().add(i);
        AtomicReference<Double> valorTotal= new AtomicReference<>(0.0);
        v.getItens().forEach(i -> valorTotal.updateAndGet(v1 -> v1 + i.getValor_unitario() * i.getQtde()));
        return valorTotal.get();
    }

    //Retorna os dados do cliente daquela venda
    public PessoaPayloadDTO getCliente(Long id){
        VendaEntity v = getVenda(id);
        PessoaPayloadDTO pessoa = new PessoaPayloadDTO();
        pessoa.setNome(v.getPessoa().getNome());
        pessoa.setSobrenome(v.getPessoa().getSobrenome());
        pessoa.setCpf(v.getPessoa().getCpf());
        pessoa.setTelefone(v.getPessoa().getTelefone());
        return pessoa;
    }

    //Adiciona um itemVenda na lista de itens daquela venda
    public void addItemVenda(@RequestBody ItemVendaDTO itemVendaDTO){ //Adiciona um item na lista de itens daquela venda
        itemVendaService.save(itemVendaDTO);
    }

    //Muda a quantidade do item
    public void updateQtde(Long id_item, int qtde_nova){ //Muda a quantidade do item (id_item) na venda (id_venda) para (qtde_nova)
        itemVendaService.getById(id_item).setQtde(qtde_nova);
    }


    public void deleteItemVenda(Long id_item){ //Remove o item com o id(id_item) da venda com o id(id_venda)
        itemVendaService.delete(id_item);
    }
}
