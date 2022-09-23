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
@RequestMapping("/venda")
public class VendaRestController {
    @Autowired
    private VendaService vendaService;

    @GetMapping
    @RequestMapping("/venda-itens")
    public List<ItemPayloadDTO> getItens(Long id){
        return vendaService.getItens(id);
    }
    @GetMapping
    @RequestMapping("/venda/id")
    public VendaDTO getDTO(@PathVariable(name = "id")Long id){
        return vendaService.getDTOById(id);
    }
    @GetMapping
    @RequestMapping("/venda-vendedor/id")
    public PessoaPayloadDTO getVendedor(@PathVariable Long id){
        return vendaService.getVendedor(id);
    }
    @GetMapping
    @RequestMapping("/venda-valor-total/id")
    public Double getValorTotal(@PathVariable Long id){
        return vendaService.getValorTotal(id);
    }
    @GetMapping
    @RequestMapping("/venda-cliente/id")
    public PessoaPayloadDTO getCliente(@PathVariable Long id){
        return vendaService.getCliente(id);
    }
    @PostMapping
    @RequestMapping("/save-venda")
    public void saveVenda(@RequestBody VendaPayloadDTO vendaDTO){
        vendaService.save(vendaDTO);
    }
    @PutMapping
    @RequestMapping("/alteracao-venda")
    public void uptadeVenda(@RequestBody VendaDTO vendaDTO){
        vendaService.update(vendaDTO);
    }
    @DeleteMapping
    @RequestMapping("/delete-venda/id")
    public void delete(@PathVariable Long id) {
        vendaService.delete(id);
    }
}


