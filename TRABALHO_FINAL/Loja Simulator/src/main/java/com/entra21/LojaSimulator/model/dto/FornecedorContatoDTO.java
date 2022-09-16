package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FornecedorContatoDTO {
    private String contato;

    public FornecedorContatoDTO(String contato) {
        this.contato = contato;
    }
}
