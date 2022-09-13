package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendaPayloadDTO {
    private LocalDate data;
    private Long id_cliente;
    private Long id_vendedor;
}
