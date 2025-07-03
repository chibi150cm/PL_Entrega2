package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Sucursal;
import com.example.PL_Entrega2.Repository.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> getSucursal(Integer id) {
        return sucursalRepository.findById(id);
    }

    public void addSucursal(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    public boolean updateSucursal(Integer id, Sucursal sucursal) {
        if (sucursalRepository.existsById(id)) {
            sucursal.setId(id);
            sucursalRepository.save(sucursal);
            return true;
        }
        return false;
    }

    public boolean deleteSucursal(Integer id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}