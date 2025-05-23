package com.example.PL_Entrega2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInventario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    private int stockActual;
    private int stockMin;
    private int stockMax;

}
