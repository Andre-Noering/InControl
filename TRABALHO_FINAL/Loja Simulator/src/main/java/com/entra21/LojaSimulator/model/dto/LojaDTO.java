package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class LojaDTO {
    private Long id;
    private String razao_social;
    private String cnpj;
    private String contato;
    private Double valor_caixa;
}
