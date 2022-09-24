
package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.model.dto.ItemPayloadDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.model.dto.VendaDTO;
import com.entra21.LojaSimulator.model.dto.VendaPayloadDTO;
import com.entra21.LojaSimulator.model.entity.VendaEntity;
import com.entra21.LojaSimulator.view.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public VendaEntity getById(Long id){
        return vendaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Venda não encontrada!"));
    }

    //Retorna nome, valor e qtde de cada item naquela venda
    public List<ItemPayloadDTO> getItens(Long id){
        List<ItemPayloadDTO> listaItens = null;
        getById(id).getItens().forEach( itemVenda -> {
            ItemPayloadDTO itemPayloadDTO = new ItemPayloadDTO();
            itemPayloadDTO.setNome(itemVenda.getItem().getNome());
            itemPayloadDTO.setValor(itemVenda.getValorUnitario());
            itemPayloadDTO.setQtde(itemVenda.getQtde());
            listaItens.add(itemPayloadDTO);
        });
        return listaItens;
    }

    //Retorna o vendedor que efetuou a venda
    public PessoaPayloadDTO getVendedor(Long id){
        VendaEntity vendaEntity = getById(id);
        PessoaPayloadDTO pessoaPayloadDTO = new PessoaPayloadDTO();
        pessoaPayloadDTO.setNome(funcionarioService.build(funcionarioService.getDTOById(vendaEntity.getFuncionario().getId())).getNome());
        pessoaPayloadDTO.setSobrenome(funcionarioService.build(funcionarioService.getDTOById(vendaEntity.getFuncionario().getId())).getSobrenome());
        return pessoaPayloadDTO;
    }

    //Retorna o valor total da Venda
    public Double getValorTotal(Long id){
        VendaEntity vendaEntity = getById(id);
        AtomicReference<Double> valorTotal= new AtomicReference<>(0.0);
        vendaEntity.getItens().forEach(itemVenda -> valorTotal.updateAndGet(v1 -> v1 + itemVenda.getValorUnitario() * itemVenda.getQtde()));
        return valorTotal.get();
    }

    //Retorna os dados do cliente daquela venda
    public PessoaPayloadDTO getCliente(Long id){
        VendaEntity vendaEntity = getById(id);
        PessoaPayloadDTO pessoa = new PessoaPayloadDTO();
        pessoa.setNome(vendaEntity.getPessoa().getNome());
        pessoa.setSobrenome(vendaEntity.getPessoa().getSobrenome());
        pessoa.setCpf(vendaEntity.getPessoa().getCpf());
        pessoa.setTelefone(vendaEntity.getPessoa().getTelefone());
        return pessoa;
    }

    public VendaDTO getDTOById(Long id){
        VendaEntity vendaEntity=getById(id);
        return new VendaDTO(vendaEntity.getId(), vendaEntity.getData(), vendaEntity.getPessoa().getId(), vendaEntity.getFuncionario().getId());
    }

    //POST
    public void save(VendaPayloadDTO vendaDTO){
        VendaEntity newVenda = new VendaEntity();
        newVenda.setData(vendaDTO.getData());
        newVenda.setPessoa(pessoaService.build(pessoaService.getDTOById(vendaDTO.getIdCliente())));
        newVenda.setFuncionario(funcionarioService.build(funcionarioService.getDTOById(vendaDTO.getIdVendedor())));
        newVenda=vendaRepository.save(newVenda);
        newVenda.getFuncionario().getLoja().setValorCaixa(newVenda.getFuncionario().getLoja().getValorCaixa()+getValorTotal(newVenda.getId()));
        newVenda.getItens().forEach(item-> item.getItem().setQtdeEstoque(item.getItem().getQtdeEstoque()-item.getQtde()));
    }

    //PUT
    public void update(VendaDTO vendaDTO){
        VendaEntity vendaEntity = getById(vendaDTO.getId());
        if(vendaDTO.getData()!=null){
            vendaEntity.setData(vendaDTO.getData());
        }
        if(vendaDTO.getIdCliente()!=null){
            vendaEntity.setPessoa(pessoaService.getById(vendaDTO.getIdCliente()));
        }

        if(vendaDTO.getIdVendedor()!=null){
            vendaEntity.setFuncionario(funcionarioService.getById(vendaDTO.getIdVendedor()));
        }
        vendaRepository.save(vendaEntity);
    }


    //DELETE
    public void delete(Long id){
        VendaEntity venda = getById(id);
        vendaRepository.delete(venda);
    }
    public void deleteItemVenda(Long idItem){
        itemVendaService.delete(idItem);
    }
}