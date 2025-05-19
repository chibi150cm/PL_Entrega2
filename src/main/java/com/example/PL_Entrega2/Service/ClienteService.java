package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Cliente;
import com.example.PL_Entrega2.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public String getAllClientes(){
        String output = "";
        for (Cliente cliente : clienteRepository.findAll()) {
            output+="ID Cliente"+cliente.getIdCliente()+"\n";
            output+="Nombre: "+cliente.getNombre()+"\n";
            output+="Apellido: "+cliente.getApellido()+"\n";
            output+="Mail: "+cliente.getMail()+"\n";
            output+="Password: "+cliente.getPassword()+"\n";
            output+="Direccion: "+cliente.getDireccion()+"\n";
            output+="Telefono: "+cliente.getTelefono()+"\n";
        }
        if(output.isEmpty()){
            return "No hay clientes";
        }else{
            return output;
        }
    }

    public String getCliente(int id){
        String output = "";
        if(clienteRepository.existsById(id)){
            Cliente cliente = clienteRepository.findById(id).get();
            output = "ID Cliente"+cliente.getIdCliente()+"\n";
            output+="Nombre: "+cliente.getNombre()+"\n";
            output+="Apellido: "+cliente.getApellido()+"\n";
            output+="Mail: "+cliente.getMail()+"\n";
            output+="Password: "+cliente.getPassword()+"\n";
            output+="Direccion: "+cliente.getDireccion()+"\n";
            output+="Telefono: "+cliente.getTelefono()+"\n";
            return output;
        }
        else{
            return "No se encontro el cliente";
        }
    }

    public String addCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return "Cliente agregado";
    }

    public String deleteCliente(int id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return "Cliente eliminado";
        }
        else{
            return "No se encontro el cliente";
        }
    }

    public String updateCliente(int id, Cliente cliente){
        if(clienteRepository.existsById(id)){
            Cliente buscado = clienteRepository.findById(id).get();
            buscado.setNombre(cliente.getNombre());
            buscado.setApellido(cliente.getApellido());
            buscado.setMail(cliente.getMail());
            buscado.setPassword(cliente.getPassword());
            buscado.setDireccion(cliente.getDireccion());
            buscado.setTelefono(cliente.getTelefono());
            clienteRepository.save(buscado);
            return "Cliente actualizado";
        }
        else{
            return "No se encontro el cliente";
        }

    }


}
