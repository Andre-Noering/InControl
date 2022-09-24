package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PedidoCompraPayloadDTO {
    private LocalDateTime data;
    private Long idFuncionario;

    public PedidoCompraPayloadDTO( LocalDateTime data, Long id_funcionario) {
        this.data = data;
        this.idFuncionario = id_funcionario;
    }
}

