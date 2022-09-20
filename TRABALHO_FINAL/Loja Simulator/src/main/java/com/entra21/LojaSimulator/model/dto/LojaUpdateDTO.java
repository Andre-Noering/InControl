package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;
@Data
public class LojaUpdateDTO {
    private Long id;
    private String contato;
    private Double valor_caixa;
    private FuncionarioEntity gerente;
    private List<ItemEntity> itens;
    private List<FornecedorEntity> fornecedores;
    private List<PessoaEntity> funcionarios;

    public LojaUpdateDTO(Long id, String contato, Double valor_caixa, FuncionarioEntity gerente, List<ItemEntity> itens, List<FornecedorEntity> fornecedores, List<PessoaEntity> funcionarios) {
        this.id = id;
        this.contato = contato;
        this.valor_caixa = valor_caixa;
        this.gerente = gerente;
        this.itens = itens;
        this.fornecedores = fornecedores;
        this.funcionarios = funcionarios;
    }
}
