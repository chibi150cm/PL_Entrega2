package com.example.PL_Entrega2;


import com.example.PL_Entrega2.Model.Cliente;
import com.example.PL_Entrega2.Model.Cliente;
import com.example.PL_Entrega2.Repository.ClienteRepository;
import com.example.PL_Entrega2.Service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteTest {
    
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    MockMvc mockMvc;
    
    @MockitoBean
    ClienteService clienteService;

    @Test
    void findAllClientesTest() {
        List<Cliente> clienteos = clienteRepository.findAll();
        assertNotNull(clienteos);
        assertEquals(1, clienteos.size());
    }

    @Test
    void checkNameClienteTest() {
        Cliente cliente = clienteRepository.findById(1).get();
        assertNotNull(cliente);
        assertEquals("Diego", cliente.getNombre());
    }

    @Test
    void getAllClientesControllerTest() {
        List<Cliente> clientesMock = new ArrayList<>();
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1); //este realmente no deberia importar ya que el id es autogenerado. Se puede jugar con esto.
        //ya que es de testeo, estos datos no se deberian guardar en la base de datos y por ende siempre añadir un cliente deberia partir
        //con el id = 1.
        clientesMock.add(cliente);

        Mockito.when(clienteService.findAll()).thenReturn(clientesMock);

        try {
            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].idCliente").value(1));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            fail();
        }
    }
}
