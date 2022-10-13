package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendaDTO {
    private Long id;
    private LocalDate data;
    private Long id_cliente;
    private Long id_vendedor;

    public VendaDTO(Long id, LocalDate data, Long id_cliente, Long id_vendedor) {
        this.id = id;
        this.data = data;
        this.id_cliente = id_cliente;
        this.id_vendedor = id_vendedor;
    }
}
