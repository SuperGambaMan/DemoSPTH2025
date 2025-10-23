package com.iesvdm.demospth2025.modelo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Pedido {

    private int id;
    private BigDecimal total;
    private LocalDate fecha;
    private Integer idCliente;
    private Integer idComercial;

}
