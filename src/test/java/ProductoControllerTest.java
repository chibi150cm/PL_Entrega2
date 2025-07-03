import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Repository.ProductoRepository;
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
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto productoExistente;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();

        productoExistente = new Producto();
        productoExistente.setNombre("Notebook");
        productoExistente.setDescripcion("Notebook HP 16GB RAM");
        productoExistente.setCantidad(10);
        productoExistente.setPrecio(1500000);
        productoExistente = productoRepository.save(productoExistente);
    }

    @Test
    void listarProductos_debeRetornarLista() throws Exception {
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Notebook"));
    }

    @Test
    void crearProducto_debeRetornarCreado() throws Exception {
        String nuevoProductoJson = """
        {
            "nombre": "Teclado",
            "descripcion": "Teclado mecánico RGB",
            "cantidad": 50,
            "precio": 80000
        }
        """;

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoProductoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarProducto_debeRetornarOk() throws Exception {
        String productoActualizadoJson = """
        {
            "nombre": "Macbook Pro",
            "descripcion": "Macbook más bonito",
            "cantidad": 5,
            "precio": 2000000
        }
        """;

        mockMvc.perform(put("/productos/{id}", productoExistente.getIdProducto())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoActualizadoJson))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarProducto_debeRetornarOk() throws Exception {
        mockMvc.perform(delete("/productos/{id}", productoExistente.getIdProducto()))
                .andExpect(status().isOk());
    }
}

