package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoCompraDTO {
    private Long id;
    private LocalDate data;
    private Long id_funcionario;

    public PedidoCompraDTO(Long id, LocalDate data, Long id_funcionario) {
        this.id = id;
        this.data = data;
        this.id_funcionario = id_funcionario;
    }
}
