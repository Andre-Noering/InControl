package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.FornecedorEntity;
import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import com.entra21.LojaSimulator.model.entity.ItemEntity;
import com.entra21.LojaSimulator.model.entity.PessoaEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class LojaDTO {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String contato;
    private Double valorCaixa;
    private FuncionarioEntity gerente;
    private List<ItemEntity> itens;
    private List<FornecedorEntity> fornecedores;
    private List<PessoaEntity> funcionarios;

    public LojaDTO(Long id, String razao_social, String cnpj, String contato, Double valor_caixa, FuncionarioEntity gerente, List<ItemEntity> itens, List<FornecedorEntity> fornecedores, List<PessoaEntity> funcionarios) {
        this.id = id;
        this.razaoSocial = razao_social;
        this.cnpj = cnpj;
        this.contato = contato;
        this.valorCaixa = valor_caixa;
        this.gerente = gerente;
        this.itens = itens;
        this.fornecedores = fornecedores;
        this.funcionarios = funcionarios;
    }
}
