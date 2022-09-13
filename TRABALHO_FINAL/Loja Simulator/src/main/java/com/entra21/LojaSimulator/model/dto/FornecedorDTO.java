package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

@Data
public class FornecedorDTO {
    private Long id;
    private String razao_social;
    private String cnpj;
    private String contato;
}
