package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FuncionarioDTO extends PessoaDTO{
    private Long id;
    private String login;
    private String senha;

}