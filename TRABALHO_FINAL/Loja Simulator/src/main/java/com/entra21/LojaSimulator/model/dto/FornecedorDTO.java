package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.LojaEntity;
import lombok.Data;

@Data
public class FornecedorDTO {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String contato;
    private Long idLoja;

    public FornecedorDTO(Long id, String razaoSocial, String cnpj, String contato, Long idLoja) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.contato = contato;
        this.idLoja = idLoja;
    }
}
