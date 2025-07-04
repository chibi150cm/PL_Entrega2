package com.example.PL_Entrega2.Service;


import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Repository.ComunaRepository;
import com.example.PL_Entrega2.Repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunaService {
    @Autowired
    ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }
    public void addComuna(Comuna comuna) {
        comunaRepository.save(comuna);
    }
    public Optional<Comuna> getComuna(int id) {
        return comunaRepository.findById(id);
    }

    public void deleteComuna(int id) {
        comunaRepository.deleteById(id);
    }

    public void updateComuna(int id, Comuna comuna) {
        Comuna comunaAux = comunaRepository.findById(id).get();
        comunaAux.setNombreComuna(comuna.getNombreComuna());

    }

}
