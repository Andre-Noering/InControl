package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class LojaPayloadDTO {
    private Long id;
    private String razao_social;
    private String cnpj;
    private String contato;
    private Double valor_caixa;

    public LojaPayloadDTO(Long id, String razao_social, String cnpj, String contato, Double valor_caixa) {
        this.id = id;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.contato = contato;
        this.valor_caixa = valor_caixa;
    }
}

