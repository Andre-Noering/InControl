package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FuncionarioPayloadDTO extends PessoaPayloadDTO{
    private String login;
    private String senha;

    public FuncionarioPayloadDTO(String nome, String sobrenome, String cpf, String telefone, Long idLoja, String login, String senha) {
        super(nome, sobrenome, cpf, telefone, idLoja);
        this.login = login;
        this.senha = senha;
    }
}
