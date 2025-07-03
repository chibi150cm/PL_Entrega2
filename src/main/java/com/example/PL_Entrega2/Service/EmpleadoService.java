package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Empleado;
import com.example.PL_Entrega2.Repository.EmpleadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public void addEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public Optional<Empleado> getEmpleado(Integer id) {
        return empleadoRepository.findById(id); // Retorna Optional<Empleado>
    }

    public boolean updateEmpleado(Integer id, Empleado empleado) {
        if (empleadoRepository.existsById(id)) {
            empleado.setIdEmpleado(id);
            empleadoRepository.save(empleado);
            return true;
        }
        return false;
    }

    public boolean deleteEmpleado(Integer id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}