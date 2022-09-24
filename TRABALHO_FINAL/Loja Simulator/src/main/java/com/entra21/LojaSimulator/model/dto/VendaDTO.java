package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendaDTO {
    private Long id;
    private LocalDate data;
    private Long idCliente;
    private Long idVendedor;

    public VendaDTO(Long id, LocalDate data, Long id_cliente, Long id_vendedor) {
        this.id = id;
        this.data = data;
        this.idCliente = id_cliente;
        this.idVendedor = id_vendedor;
    }
}
