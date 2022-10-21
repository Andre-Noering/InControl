package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VendaPayloadDTO {
    private Long id;
    private LocalDateTime data;
    private Long id_cliente;
    private Long id_vendedor;

    public VendaPayloadDTO(Long id,LocalDateTime data, Long id_cliente, Long id_vendedor) {
        this.id=id;
        this.data = data;
        this.id_cliente = id_cliente;
        this.id_vendedor = id_vendedor;
    }
}
