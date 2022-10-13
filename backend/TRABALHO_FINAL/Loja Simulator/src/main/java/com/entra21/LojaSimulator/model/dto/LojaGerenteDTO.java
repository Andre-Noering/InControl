package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.FuncionarioEntity;
import lombok.Data;

@Data
public class LojaGerenteDTO {
    private FuncionarioEntity gerente;

    public LojaGerenteDTO(FuncionarioEntity gerente) {
        this.gerente = gerente;
    }
}
