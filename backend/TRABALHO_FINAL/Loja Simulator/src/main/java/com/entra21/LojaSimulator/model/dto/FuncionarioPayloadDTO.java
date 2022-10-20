package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FuncionarioPayloadDTO extends PessoaDTO{
    private Long id;
    private String login;
    private String senha;
    private boolean ativo;

    public FuncionarioPayloadDTO(Long id, String login, String senha, boolean ativo) {
        this.id=id;
        this.login = login;
        this.senha = senha;
        this.ativo=ativo;
    }

    public FuncionarioPayloadDTO(){

    }
}
