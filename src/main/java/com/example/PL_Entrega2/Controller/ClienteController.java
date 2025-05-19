package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Cliente;
import com.example.PL_Entrega2.Service.ClienteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/clientes")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public String obtenerCliente(@PathVariable Integer id) {
        return clienteService.getCliente(id);
    }

    @PostMapping
    public String crearCliente(@RequestBody Cliente cliente) {
        return clienteService.addCliente(cliente);
    }

    @PutMapping("/{id}")
    public String actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
            return clienteService.updateCliente(id, cliente);
        }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable Integer id) {
        return clienteService.deleteCliente(id);
    }
}
