package com.example.PL_Entrega2.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrito {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private int idCarrito;
    @OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cliente cliente;
    @ManyToMany
    @JoinTable(name = "Carrito_Producto",
    joinColumns = @JoinColumn(name = "carrito_id"),
    inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    //conjunto de elementos unicos
    //hashset es de rapido acceso, sin orden
    //se debe llamar producto, no productos, ya que en Producto esta mapped by producto
    private Set<Producto> producto = new HashSet<>();


}
