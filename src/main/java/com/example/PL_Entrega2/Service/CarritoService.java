package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Carrito;
import com.example.PL_Entrega2.Repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

//probe utilizar el objeto completo en vez de las variables por variables
public class CarritoService {
    @Autowired
    CarritoRepository carritoRepository;

    public List<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }
    public Optional<Carrito> getCarritoById(int id) {
        return carritoRepository.findById(id);
    }
    public Carrito addCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public boolean deleteCarrito(int id) {
        if (carritoRepository.existsById(id)) {
            carritoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
