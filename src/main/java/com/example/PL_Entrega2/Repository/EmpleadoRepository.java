package com.example.PL_Entrega2.Repository;

import org.springframework.stereotype.Repository;
import com.example.PL_Entrega2.Model.Empleado;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpleadoRepository {
    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoRepository(){
    }

    public String getEmpleado(int id){
        for (Empleado empleado : empleados){
            if (empleado.getIdEmpleado() == id){
                return empleado.toString();
            }
        }
        return "Empleado no existe.";
    }

    public String addEmpleado (Empleado empleado){
        empleados.add(empleado);
        return "Empleado agregado con exito.";
    }

    public String deleteEmpleado (int id){
        for (Empleado empleado : empleados){
            if (empleado.getIdEmpleado() == id) {
                empleados.remove(empleado);
                return "Empleado eliminado con exito.";
            }
        }
        return "Empleado no existe.";
    }

    public String updateEmpleado(Empleado nuevoEmpleado){
        for (Empleado empleado : empleados){
            if (empleado.getNombre().equalsIgnoreCase(nuevoEmpleado.getNombre())){
                int index = empleados.indexOf(empleado);
                empleados.set(index, nuevoEmpleado);
                return "Empleado actualizado con exito.";
            }
        } return "Empleado no existe.";
    }

    public String getEmpleado(){
        String output = "";
        for (Empleado empleado : empleados) {
            output += "Id Empleado: " + empleado.getIdEmpleado() + "\n";
            output += "Nombre: " + empleado.getNombre() + "\n";
            output += "Apellido: " + empleado.getApellido() + "\n";
            output += "Mail: " + empleado.getMail() + "\n";
            output += "Cargo: " + empleado.getCargo() + "\n";
            output += "Sucursal: " + empleado.getSucursal() + "\n";
        }
        if(output.isEmpty()){
            return "Empleado no existe.";
        }else{
            return output;
        }
    }
}
