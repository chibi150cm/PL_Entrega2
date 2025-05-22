package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Empleado;
import com.example.PL_Entrega2.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public String getAllEmpleados() {
        String output = "";
        for (Empleado empleado : empleadoRepository.findAll()) {
            output+="ID Empleado: "+empleado.getIdEmpleado()+"\n";
            output+="Nombre: "+empleado.getNombre()+"\n";
            output+="Apellido: "+empleado.getApellido()+"\n";
            output+="mail: "+empleado.getMail()+"\n";
            output+="password: "+empleado.getPassword()+"\n";
            output+="cargo: "+empleado.getCargo()+"\n";
            output+="sucursal: "+empleado.getSucursal()+"\n";
        }
        if (output.isEmpty()){
            return "No se encontraron empleados";
        }else{
            return output;
        }
    }

    public String getEmpleado(int id) {
        String output = "";
        if (empleadoRepository.existsById(id)) {
            Empleado empleado = empleadoRepository.findById(id).get();
            output = "ID Empleado: "+empleado.getIdEmpleado()+"\n";
            output += "Nombre: "+empleado.getNombre()+"\n";
            output += "Apellido: "+empleado.getApellido()+"\n";
            output += "Mail: "+empleado.getMail()+"\n";
            output += "Password: "+empleado.getPassword()+"\n";
            output += "Cargo: "+empleado.getCargo()+"\n";
            output += "Sucursal: "+empleado.getSucursal()+"\n";
            return output;
        }else{
            return "No se encontro empleado";
        }
    }

    public String addEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
        return "Empleado agregado";
    }

    public String deleteEmpleado(int id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return "Empleado eliminado";
        }else{
            return "No se encontro empleado";
        }
    }

    public String updateEmpleado(int id, Empleado empleado) {
        if (empleadoRepository.existsById(id)) {
            Empleado buscado = empleadoRepository.findById(id).get();
            buscado.setNombre(empleado.getNombre());
            buscado.setApellido(empleado.getApellido());
            buscado.setMail(empleado.getMail());
            buscado.setPassword(empleado.getPassword());
            buscado.setCargo(empleado.getCargo());
            buscado.setSucursal(empleado.getSucursal());
            empleadoRepository.save(buscado);
            return "Empleado actualizado";
        }else{
            return "No se encontro empleado";
        }
    }
}
