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

    public String addCliente(Cliente cliente){return clienteRepository.addCliente(cliente);}
    public String updateCliente(Cliente cliente){return clienteRepository.updateCliente(cliente);}
    public String deleteCliente(int id){return clienteRepository.deleteCliente(id);}
    public String getCliente(int id){return clienteRepository.getCliente(id);}
    public String getAllUsers(){return clienteRepository.getCliente();}
}
