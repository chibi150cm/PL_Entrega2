package com.example.PL_Entrega2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int precio;
    @OneToMany (mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Inventario> inventarios = new ArrayList<>();

    @ManyToMany(mappedBy = "producto")
    private Set<Carrito> carritos = new HashSet<>();
}
