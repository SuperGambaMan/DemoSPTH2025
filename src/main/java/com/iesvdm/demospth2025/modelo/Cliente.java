package com.iesvdm.demospth2025.modelo;

import lombok.Data;

@Data
public class Cliente {

    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;

}
