package com.example.PL_Entrega2.Repository;

import org.springframework.stereotype.Repository;
import com.example.PL_Entrega2.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {
    private List <Cliente> clientes = new ArrayList<>();

    public ClienteRepository(){
    }

    public String getCliente (int id){
        for (Cliente cliente : clientes){
            if (cliente.getIdCliente() == id){
                return  cliente.toString();
            }
        }
        return "Cliente no existe.";
    }

    public String addCliente (Cliente cliente){
        clientes.add(cliente);
        return "Cliente agregado con éxito.";
    }

    public String deleteCliente (int id){
        for (Cliente cliente : clientes){
            if (cliente.getIdCliente() == id) {
                clientes.remove(cliente);
                return "Cliente eliminado con éxito.";
            }
        }
        return "Cliente no existe";
    }

    public String updateCliente(Cliente nuevoCliente){
        for (Cliente cliente : clientes){
            if (cliente.getNombre().equalsIgnoreCase(nuevoCliente.getNombre())){
                int index = clientes.indexOf(cliente);
                clientes.set(index, nuevoCliente);
                return "Cliente actualizado con exito.";
            }
        } return "Cliente no encontrado";
    }

    public String getCliente(){
        String output = "";
        for (Cliente cliente : clientes) {
            output += "Id Cliente: " + cliente.getIdCliente() + "\n";
            output += "Nombre: " + cliente.getNombre() + "\n";
            output += "Apellido: " + cliente.getApellido() + "\n";
            output += "Direccion: " + cliente.getDireccion() + "\n";
            output += "Telefono: " + cliente.getTelefono() + "\n";
        }
        if(output.isEmpty()){
            return "El cliente no existe.";
        }else{
            return output;
        }
    }



}
