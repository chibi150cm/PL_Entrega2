package com.example.PL_Entrega2;


import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Repository.ComunaRepository;
import com.example.PL_Entrega2.Service.ComunaService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ComunaTest {


    @Autowired
    ComunaRepository comunaRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ComunaService comunaService;

    @Test
    void findAllComunasTest() {
        List<Comuna> comunas = comunaRepository.findAll();
        assertNotNull(comunas);
        assertEquals(1, comunas.size());
    }

    @Test
    void checkNameComunaTest() {
        Comuna comuna = comunaRepository.findById(1).get();
        assertNotNull(comuna);
        assertEquals("Maipu", comuna.getNombreComuna());
    }

    @Test
    void getAllComunasControllerTest() {
        List<Comuna> comunasMock = new ArrayList<>();
        Comuna comuna = new Comuna();
        comuna.setIdComuna(1); //este realmente no deberia importar ya que el id es autogenerado. Se puede jugar con esto.
        //ya que es de testeo, estos datos no se deberian guardar en la base de datos y por ende siempre añadir una comuna deberia partir
        //con el id = 1.
        comunasMock.add(comuna);

        Mockito.when(comunaService.findAll()).thenReturn(comunasMock);

        try {
            mockMvc.perform(get("/comunas"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].idComuna").value(1));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            fail();
        }
    }
}
