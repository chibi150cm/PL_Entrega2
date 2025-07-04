package com.example.PL_Entrega2.Service;

import com.example.PL_Entrega2.Model.Cliente;
import com.example.PL_Entrega2.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public void addCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    public Optional<Cliente> getCliente(int id) {
        return clienteRepository.findById(id);
    }

    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }

    public void updateCliente(int id, Cliente cliente) {
        Cliente clienteAux = clienteRepository.findById(id).get();
        clienteAux.setNombre(cliente.getNombre());
        clienteAux.setApellido(cliente.getApellido());
        clienteAux.setMail(cliente.getMail());
        clienteAux.setPassword(cliente.getPassword());
        clienteAux.setDireccion(cliente.getDireccion());
        clienteAux.setTelefono(cliente.getTelefono());
    }


}
