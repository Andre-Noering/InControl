package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoCompraPayloadDTO {
    private LocalDate data;
    private Long idFuncionario;

    public PedidoCompraPayloadDTO( LocalDate data, Long id_funcionario) {
        this.data = data;
        this.idFuncionario = id_funcionario;
    }
}

