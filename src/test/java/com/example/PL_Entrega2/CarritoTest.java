package com.example.PL_Entrega2;

import com.example.PL_Entrega2.Model.Carrito;
import com.example.PL_Entrega2.Repository.CarritoRepository;
import com.example.PL_Entrega2.Service.CarritoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarritoTest {

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CarritoService carritoService;

    //estos test son distintos de los demas ya que trabajan con el objeto completo
    @Test
    void findAllCarritosTest() {
        carritoRepository.deleteAll(); //en caso de correr el test multiple veces
        //esto es para que el profe no tenga que añadir un carrito manualmente a la base de datos
        //ya que esta esta vacia. A no ser que le demos una base de datos con datos.
        Carrito carrito = new Carrito();
        carritoRepository.save(carrito);

        List<Carrito> carritos = carritoRepository.findAll();
        assertNotNull(carritos);
        assertEquals(1,carritos.size());
    }


    /*lamentablemente, el metodo del profesor no funciona aqui. Ya que espera un String
    Y modificue el metodo para darme el objeto completo, asi que espera un json.
     */
    @Test
    void getAllCarritosControllerTest() {
        List<Carrito> carritosMock = new ArrayList<>();
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1); //este realmente no deberia importar ya que el id es autogenerado. Se puede jugar con esto.
        //ya que es de testeo, estos datos no se deberian guardar en la base de datos y por ende siempre añadir un carrito deberia partir
        //con el id = 1.
        carritosMock.add(carrito);

        Mockito.when(carritoService.getAllCarritos()).thenReturn(carritosMock);

        try {
            mockMvc.perform(get("/carritos"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].idCarrito").value(1));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            fail();
        }
    }
}
