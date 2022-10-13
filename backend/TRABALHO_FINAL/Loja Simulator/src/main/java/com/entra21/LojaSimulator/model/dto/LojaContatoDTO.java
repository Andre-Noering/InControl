package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class LojaContatoDTO {
    private String contato;

    public LojaContatoDTO(String contato) {
        this.contato = contato;
    }
}
