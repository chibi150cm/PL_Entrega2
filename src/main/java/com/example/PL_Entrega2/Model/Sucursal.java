package com.example.PL_Entrega2.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreSucursal;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

}
