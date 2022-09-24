package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VendaPayloadDTO {
    private LocalDateTime data;
    private Long idCliente;
    private Long idVendedor;
}
