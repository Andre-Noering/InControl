package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FuncionarioDTO extends PessoaDTO{
    private Long idPessoa;
    private String login;
    private String senha;
    private boolean ativo;

    public FuncionarioDTO(Long id, String login, String senha) {
        this.idPessoa=id;
        this.login = login;
        this.senha = senha;
    }

    public FuncionarioDTO(){

    }
}
