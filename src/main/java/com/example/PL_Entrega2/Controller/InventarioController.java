package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Inventario;
import com.example.PL_Entrega2.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public String listarInventario() {
        return inventarioService.getAllInventario();
    }

    @GetMapping("/{id}")
    public String obtenerInventario(@PathVariable Integer id) {
        return inventarioService.getInventario(id);
    }

    @PostMapping
    public String agregarInventario(@RequestBody Inventario inventario) {
        return inventarioService.addInventario(inventario);
    }

    @PutMapping("/{id}")
    public String actualizarInventario(@RequestBody Inventario inventario) {
        return inventarioService.updateInventario(inventario);
    }

    @DeleteMapping("/{id}")
    public String eliminarInventario(@PathVariable Integer id) {
        return inventarioService.deleteInventario(id);
    }
}