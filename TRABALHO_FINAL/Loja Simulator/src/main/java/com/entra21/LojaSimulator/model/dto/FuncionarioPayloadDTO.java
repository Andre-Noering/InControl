package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FuncionarioPayloadDTO extends PessoaPayloadDTO{
    private String login;
    private String senha;
}
