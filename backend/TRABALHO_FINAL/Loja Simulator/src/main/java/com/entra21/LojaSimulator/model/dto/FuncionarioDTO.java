package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FuncionarioDTO extends PessoaDTO{
    private String login;
    private String senha;

    public FuncionarioDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public FuncionarioDTO(){

    }
}
