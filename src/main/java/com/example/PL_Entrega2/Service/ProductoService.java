package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public String addProducto(Producto producto) {
        productoRepository.save(producto);
        return "Producto agregado";
    }

    public String updateProducto(Producto producto) {
        if (productoRepository.existsById(producto.getIdProducto())) {
            productoRepository.save(producto);
            return "Producto actualizado";
        }
        return "Producto no encontrado";
    }

    public String deleteProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return "Producto eliminado";
        }
        return "Producto no encontrado";
    }

    public String getProducto(int id) {
        return productoRepository.findById(id)
                .map(Producto::toString)
                .orElse("Producto no encontrado");
    }

    //falta un if si es que no encuentra nada
    public String getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.toString();
    }
}