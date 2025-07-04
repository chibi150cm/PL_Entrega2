package com.example.PL_Entrega2;


import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Repository.ComunaRepository;
import com.example.PL_Entrega2.Service.ComunaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @BeforeEach //bien util
    void setup() {
        comunaRepository.deleteAll();

        Comuna comuna = new Comuna();
        comuna.setNombreComuna("Maipu");
        comunaRepository.save(comuna);
    }


    @Test
    void getComunaByIdTest1() throws Exception {
        Comuna comuna = new Comuna();
        comuna.setIdComuna(1);
        comuna.setNombreComuna("Maipu");

        Mockito.when(comunaService.getComuna(1)).thenReturn(Optional.of(comuna));

        mockMvc.perform(get("/comunas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/hal+json"))
                .andExpect(jsonPath("$.idComuna").value(1))
                .andExpect(jsonPath("$.nombreComuna").value("Maipu"));
    }

    @Test
    void checkNameComunaTest() {
        Comuna comuna = comunaRepository.findById(1).get();
        assertNotNull(comuna);
        assertEquals("Maipu", comuna.getNombreComuna());
    }

    @Test
    void findAllComunasTest() {
        List<Comuna> comunas = comunaRepository.findAll();
        assertNotNull(comunas);
        assertEquals(1, comunas.size());
    }



    @Test
    //no funciona, antes funcionaba y ahora no :C
    void getAllComunasControllerTest() {
        List<Comuna> comunasMock = new ArrayList<>();
        Comuna comuna = new Comuna();
        comuna.setIdComuna(1);
        comuna.setNombreComuna("Las Condes");
        comunasMock.add(comuna);

        Mockito.when(comunaService.findAll()).thenReturn(comunasMock);

        try {
            mockMvc.perform(get("/comunas"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith("application/hal+json"))
                    .andExpect(jsonPath("$._embedded.comunas.length()").value(1))
                    .andExpect(jsonPath("$._embedded.comunas[0].idComuna").value(1))
                    .andExpect(jsonPath("$._embedded.comunas[0].nombreComuna").value("Las Condes"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            fail();
        }
    }
}
