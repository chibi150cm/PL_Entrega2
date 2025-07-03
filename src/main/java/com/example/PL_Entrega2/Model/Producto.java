package com.example.PL_Entrega2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Integer precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("producto") // Alternativa a @JsonIgnore
    private List<Inventario> inventarios = new ArrayList<>();

    @ManyToMany(mappedBy = "producto") // Nota: cambié a "productos" para coincidir con el nombre de la relación
    @JsonIgnore
    private Set<Carrito> carritos = new HashSet<>();
}