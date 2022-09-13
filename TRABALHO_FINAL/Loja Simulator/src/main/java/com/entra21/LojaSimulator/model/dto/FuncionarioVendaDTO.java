package com.entra21.LojaSimulator.model.dto;

import com.entra21.LojaSimulator.model.entity.VendaEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioVendaDTO {
    private List<VendaEntity> vendaEntity;

    public FuncionarioVendaDTO(List<VendaEntity> vendaEntity) {
        this.vendaEntity = vendaEntity;
    }
}
