package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Sucursal;
import com.example.PL_Entrega2.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public String listarSucursales() {
        return sucursalService.getAllSucursales();
    }

    @GetMapping("/{id}")
    public String obtenerSucursal(@PathVariable Integer id) {
        return sucursalService.getSucursal(id);
    }

    @PostMapping
    public String crearSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.addSucursal(sucursal);
    }

    @PutMapping("/{id}")
    public String actualizarSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.updateSucursal(sucursal);
    }

    @DeleteMapping("/{id}")
    public String eliminarSucursal(@PathVariable Integer id) {
        return sucursalService.deleteSucursal(id);
    }
}
