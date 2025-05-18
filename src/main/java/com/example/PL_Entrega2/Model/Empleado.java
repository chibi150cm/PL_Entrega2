package com.example.PL_Entrega2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String sucursal;
}
