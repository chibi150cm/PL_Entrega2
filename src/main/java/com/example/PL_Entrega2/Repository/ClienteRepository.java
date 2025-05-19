package com.example.PL_Entrega2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.PL_Entrega2.Model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}