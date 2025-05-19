package com.example.PL_Entrega2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String direccion;
    private int telefono;
}
