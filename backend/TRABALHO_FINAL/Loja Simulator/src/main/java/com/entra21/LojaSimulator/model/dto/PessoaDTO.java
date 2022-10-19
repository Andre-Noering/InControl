package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class PessoaDTO {
    private Long idPessoa;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
}
