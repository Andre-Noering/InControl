package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.model.dto.ItemPayloadDTO;
import com.entra21.LojaSimulator.model.dto.PessoaPayloadDTO;
import com.entra21.LojaSimulator.model.dto.VendaDTO;
import com.entra21.LojaSimulator.model.dto.VendaPayloadDTO;
import com.entra21.LojaSimulator.view.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/venda")
public class VendaRestController {
    @Autowired
    private VendaService vendaService;

    @GetMapping(name = "/venda-itens")
    public List<ItemPayloadDTO> getItens(Long id){
        return vendaService.getItens(id);
    }
    @GetMapping(name = "/venda/${id}")
    public VendaDTO getDTO(@PathVariable(name = "id")Long id){
        return vendaService.getDTOById(id);
    }
    @GetMapping(name = "/venda-vendedor/${id}")
    public PessoaPayloadDTO getVendedor(@PathVariable Long id){
        return vendaService.getVendedor(id);
    }
    @GetMapping(name = "/venda-valor-total/${id}")
    public Double getValorTotal(@PathVariable Long id){
        return vendaService.getValorTotal(id);
    }
    @GetMapping(name = "/venda-cliente/${id}")
    public PessoaPayloadDTO getCliente(@PathVariable Long id){
        return vendaService.getCliente(id);
    }
    @PostMapping(name = "/save-venda")
    public void saveVenda(@RequestBody VendaPayloadDTO vendaDTO){
        vendaService.save(vendaDTO);
    }
    @PutMapping(name = "/alteracao-venda")
    public void uptadeVenda(@RequestBody VendaDTO vendaDTO){
        vendaService.update(vendaDTO);
    }
    @DeleteMapping(name = "delete-venda/${id}")
    public void delete(@PathVariable Long id) {
        vendaService.delete(id);
    }
}


