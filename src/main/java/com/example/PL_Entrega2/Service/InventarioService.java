package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Inventario;
import com.example.PL_Entrega2.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getAllInventario() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> getInventario(Integer id) {
        return inventarioRepository.findById(id);
    }

    public void addInventario(Inventario inventario) {
        inventarioRepository.save(inventario);
    }

    public boolean updateInventario(Integer id, Inventario inventario) {
        if (inventarioRepository.existsById(id)) {
            inventario.setIdInventario(id);
            inventarioRepository.save(inventario);
            return true;
        }
        return false;
    }

    public boolean deleteInventario(Integer id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
