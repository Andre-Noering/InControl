package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class LojaPayloadDTO {
    private String razao_social;
    private String cnpj;
    private String contato;
    private Double valor_caixa;
    private Long id_funcionario;

    public LojaPayloadDTO(String razao_social, String cnpj, String contato, Double valor_caixa, Long id_funcionario) {
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.contato = contato;
        this.valor_caixa = valor_caixa;
        this.id_funcionario=id_funcionario;
    }
}

