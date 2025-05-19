package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Empleado;
import com.example.PL_Entrega2.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public String addEmpleado(Empleado empleado){return empleadoRepository.addEmpleado(empleado);}
    public String updateEmpleado(Empleado empleado){return empleadoRepository.updateEmpleado(empleado);}
    public String deleteEmpleado(int id){return empleadoRepository.deleteEmpleado(id);}
    public String getEmpleado(int id){return empleadoRepository.getEmpleado(id);}
    public String getAllEmpleados(){return empleadoRepository.getEmpleado();}
}
