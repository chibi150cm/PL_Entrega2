package com.example.PL_Entrega2.Repository;


import com.example.PL_Entrega2.Model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
