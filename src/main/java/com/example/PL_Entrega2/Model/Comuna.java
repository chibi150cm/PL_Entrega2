package com.example.PL_Entrega2.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComuna;
    private String nombreComuna;


    //Puede que de porblemas por el tema del array list

    //mappedby indica quiein es el dueño
    //el resta es para borrar la sucursusal si es que se borra alguna de las llaves
    @OneToMany(mappedBy = "comuna", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Sucursal> sucursales = new ArrayList<>();
}
