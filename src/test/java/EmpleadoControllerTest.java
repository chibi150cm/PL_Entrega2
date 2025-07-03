import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.PL_Entrega2.Model.Empleado;
import com.example.PL_Entrega2.PlEntrega2Application;
import com.example.PL_Entrega2.Repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = PlEntrega2Application.class)
@AutoConfigureMockMvc
@Transactional
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Empleado empleadoExistente;

    @BeforeEach
    void setUp() {
        // Puse algunas variables a probar, no sé si requiera todas, pero al mínimo funciona :D
        empleadoRepository.deleteAll();

        empleadoExistente = new Empleado();
        empleadoExistente.setNombre("Carlos");
        empleadoExistente.setMail("carlitoslechuga@perfulandia.cl");
        empleadoExistente.setCargo("Gerente");
        empleadoExistente = empleadoRepository.save(empleadoExistente);
    }

    @Test
    void listarEmpleados_debeRetornarLista() throws Exception {
        mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Carlos"));
    }

    @Test
    void crearEmpleado_debeRetornarCreado() throws Exception {
        String nuevoEmpleadoJson = """
        {
            "nombre": "Mario",
            "mail": "mariohugo@perfulandia.cl",
            "cargo": "Vendedor"
        }
        """;

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoEmpleadoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarEmpleado_debeRetornarOk() throws Exception {
        String empleadoActualizadoJson = """
        {
            "nombre": "Pedro",
            "mail": "pedrohugoperfulandia.cl",
            "cargo": "RRHH"
        }
        """;

        mockMvc.perform(put("/empleados/{id}", empleadoExistente.getIdEmpleado())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empleadoActualizadoJson))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarEmpleado_debeRetornarOk() throws Exception {
        mockMvc.perform(delete("/empleados/{id}", empleadoExistente.getIdEmpleado()))
                .andExpect(status().isOk());
    }
}