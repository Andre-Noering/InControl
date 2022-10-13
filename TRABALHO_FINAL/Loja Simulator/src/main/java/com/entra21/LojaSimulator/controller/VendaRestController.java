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
@RequestMapping("/{razaoSocial}/vendas")
public class VendaRestController {
    @Autowired
    private VendaService vendaService;

    @GetMapping("/{id}/venda-itens")
    public List<ItemPayloadDTO> getItens(@PathVariable Long id){
        return vendaService.getItens(id);
    }

    @GetMapping("/{id}")
    public VendaDTO getDTO(@PathVariable Long id){
        return vendaService.getDTOById(id);
    }

    @GetMapping("/{id}/venda-vendedor")
    public PessoaPayloadDTO getVendedor(@PathVariable Long id){
        return vendaService.getVendedor(id);
    }

    @GetMapping("/{id}/venda-valor-total")
    public Double getValorTotal(@PathVariable Long id){
        return vendaService.getValorTotal(id);
    }

    @GetMapping("/{id}/venda-cliente")
    public PessoaPayloadDTO getCliente(@PathVariable Long id){
        return vendaService.getCliente(id);
    }

    @PostMapping("/adicionar")
    public void save(@RequestBody VendaPayloadDTO vendaDTO){
        vendaService.save(vendaDTO);
    }
    @PostMapping("/finalizar")
    public void finalizar(@RequestBody Long id){
        vendaService.finalizar(id);
    }

    @PutMapping("/atualizar")
    public void update(@RequestBody VendaDTO vendaDTO){
        vendaService.update(vendaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vendaService.delete(id);
    }

}


