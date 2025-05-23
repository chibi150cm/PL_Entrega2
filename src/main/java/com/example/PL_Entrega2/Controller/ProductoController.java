package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping
    public String listarProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public String obtenerProducto(@PathVariable Integer id) {
        return productoService.getProducto(id);
    }

    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        return productoService.addProducto(producto);
    }

    @PutMapping("/{id}")
    public String actualizarProducto(@RequestBody Producto producto) {
        return productoService.updateProducto(producto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        return productoService.deleteProducto(id);
    }
}