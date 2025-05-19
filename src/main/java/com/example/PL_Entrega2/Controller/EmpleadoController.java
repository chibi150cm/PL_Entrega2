package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Empleado;
import com.example.PL_Entrega2.Service.EmpleadoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarEmpleados(){
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("{id}")
    public String getEmpleado(@PathVariable Integer id){
        return empleadoService.getEmpleado(id);
    }

    @PostMapping
    public String addEmpleado(@RequestBody Empleado empleado){
        return empleadoService.addEmpleado(empleado);
    }

    @PutMapping({"/{id}"})
    public String updateEmpleado(@PathVariable int id, @RequestBody Empleado empleado){
        return empleadoService.updateEmpleado(id, empleado);
    }

    @DeleteMapping("/{id}")
    public String deleteEmpleado(@PathVariable int id){
        return empleadoService.deleteEmpleado(id);
    }
}
