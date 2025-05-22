package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Sucursal;
import com.example.PL_Entrega2.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public String addSucursal(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
        return "Sucursal agregada con exito";
    }

    public String updateSucursal(Sucursal sucursal) {
        if (sucursalRepository.existsById(sucursal.getId())) {
            sucursalRepository.save(sucursal);
            return "Sucursal actualizada";
        }
        return "Sucursal no existe";
    }

    public String deleteSucursal(int id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            return "Sucursal eliminada";
        }
        return "Sucursal no encontrada";
    }

    public String getSucursal(int id) {
        return sucursalRepository.findById(id)
                .map(Sucursal::toString)
                .orElse("Sucursal no encontrada");
    }

    public String getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        if (sucursales.isEmpty()) {
            return "No hay sucursales registradas";
        }
        return sucursales.toString();
    }
}
