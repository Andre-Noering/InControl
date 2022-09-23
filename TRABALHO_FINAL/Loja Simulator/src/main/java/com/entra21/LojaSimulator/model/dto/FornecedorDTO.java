package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.LojaEntity;
import lombok.Data;

@Data
public class FornecedorDTO {
    private Long id;
    private String razao_social;
    private String cnpj;
    private String contato;
    private Long id_loja;

    public FornecedorDTO(Long id, String razao_social, String cnpj, String contato, Long id_loja) {
        this.id = id;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.contato = contato;
        this.id_loja = id_loja;
    }
}
