package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.LojaEntity;
import lombok.Data;

@Data
public class FornecedorPayloadDTO {
    private Long id;
    private String razao_social;
    private String cnpj;
    private String contato;
    private Long idLoja;

    public FornecedorPayloadDTO(Long id, String razao_social, String cnpj, String contato, Long loja) {
        this.id = id;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.contato = contato;
        this.idLoja = loja;
    }
}
