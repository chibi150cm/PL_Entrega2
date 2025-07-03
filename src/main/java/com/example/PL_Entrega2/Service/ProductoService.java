package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public String addProducto(Producto producto) {
        productoRepository.save(producto);
        return "Producto agregado";
    }

    public boolean updateProducto(int id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setIdProducto(id);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean deleteProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Producto> getProducto(int id) {
        return productoRepository.findById(id);
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
}