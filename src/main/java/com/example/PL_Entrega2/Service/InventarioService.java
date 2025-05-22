package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Inventario;
import com.example.PL_Entrega2.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public String addInventario(Inventario inventario) {
        inventarioRepository.save(inventario);
        return "Inventario agregado";
    }

    public String updateInventario(Inventario inventario) {
        if (inventarioRepository.existsById(inventario.getIdInventario())) {
            inventarioRepository.save(inventario);
            return "Inventario actualizado";
        }
        return "Inventario no encontrado";
    }

    public String deleteInventario(int id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return "Inventario eliminado";
        }
        return "Inventario no encontrado";
    }

    public String getInventario(int id) {
        return inventarioRepository.findById(id)
                .map(Inventario::toString)
                .orElse("Inventario no encontrado");
    }

    // se le hacía una lista también?
    public String getAllInventario() {
        List<Inventario> inventario = inventarioRepository.findAll();
        if (inventario.isEmpty()) {
            return "No hay productos en el inventario.";
        }
        return inventario.toString();
    }
}
