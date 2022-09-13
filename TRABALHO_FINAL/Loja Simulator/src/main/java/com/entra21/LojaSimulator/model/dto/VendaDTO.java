package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendaDTO {
    private Long id;
    private LocalDate data;
    private Long id_cliente;
    private Long id_vendedor;
}
