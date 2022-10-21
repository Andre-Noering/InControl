package com.entra21.LojaSimulator.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VendaDTO {
    private Long id;
    private LocalDateTime data;
    private Long id_cliente;
    private Long id_vendedor;
    private String nome_cliente;
    private String nome_vendedor;
    private Double valor;


    public VendaDTO(Long id, LocalDateTime data, Long id_cliente, Long id_vendedor) {
        this.id = id;
        this.data = data;
        this.id_cliente = id_cliente;
        this.id_vendedor = id_vendedor;
    }

    public VendaDTO(Long id, LocalDateTime data, Long id_cliente, Long id_vendedor, String nome_cliente, String nome_vendedor, Double valor) {
        this.id = id;
        this.data = data;
        this.id_cliente = id_cliente;
        this.id_vendedor = id_vendedor;
        this.nome_cliente = nome_cliente;
        this.nome_vendedor = nome_vendedor;
        this.valor = valor;
    }
}
