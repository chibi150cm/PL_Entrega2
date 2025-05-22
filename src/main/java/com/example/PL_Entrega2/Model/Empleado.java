package com.example.PL_Entrega2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String cargo;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}
