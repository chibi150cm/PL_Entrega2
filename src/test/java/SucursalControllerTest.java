import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.PL_Entrega2.Model.Sucursal;
import com.example.PL_Entrega2.Repository.SucursalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = com.example.PL_Entrega2.PlEntrega2Application.class)
@AutoConfigureMockMvc
@Transactional
public class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Sucursal sucursalExistente;

    @BeforeEach
    void setUp() {
        sucursalRepository.deleteAll();

        sucursalExistente = new Sucursal();
        sucursalExistente.setNombreSucursal("Sucursal Casa Central");
        sucursalExistente = sucursalRepository.save(sucursalExistente);
    }

    @Test
    void listarSucursales_debeRetornarLista() throws Exception {
        mockMvc.perform(get("/sucursales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreSucursal").value("Sucursal Casa Central"));
    }

    @Test
    void crearSucursal_debeRetornarCreado() throws Exception {
        String nuevaSucursalJson = """
        {
            "nombreSucursal": "Sucursal Norte"
        }
        """;

        mockMvc.perform(post("/sucursales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevaSucursalJson))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarSucursal_debeRetornarOk() throws Exception {
        String sucursalActualizadaJson = """
        {
            "nombreSucursal": "Sucursal Sur"
        }
        """;

        mockMvc.perform(put("/sucursales/{id}", sucursalExistente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sucursalActualizadaJson))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarSucursal_debeRetornarOk() throws Exception {
        mockMvc.perform(delete("/sucursales/{id}", sucursalExistente.getId()))
                .andExpect(status().isOk());
    }

}