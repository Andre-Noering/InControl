package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PedidoCompraDTO {
    private Long id;
    private LocalDateTime data;
    private Long id_funcionario;

    public PedidoCompraDTO(Long id, LocalDateTime data, Long id_funcionario) {
        this.id = id;
        this.data = data;
        this.id_funcionario = id_funcionario;
    }
}
