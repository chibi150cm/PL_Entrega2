import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.PL_Entrega2.Model.Inventario;
import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Model.Sucursal;
import com.example.PL_Entrega2.Repository.InventarioRepository;
import com.example.PL_Entrega2.Repository.ProductoRepository;
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
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inventarioExistente;

    @BeforeEach
    void setUp() {
        inventarioRepository.deleteAll();
        productoRepository.deleteAll();
        sucursalRepository.deleteAll();

        Producto producto = new Producto();
        producto.setNombre("Monitor");
        producto.setDescripcion("Modelo XYZ");
        producto = productoRepository.save(producto);

        Sucursal sucursal = new Sucursal();
        sucursal.setNombreSucursal("Sucursal Central");
        sucursal = sucursalRepository.save(sucursal);

        inventarioExistente = new Inventario();
        inventarioExistente.setProducto(producto);
        inventarioExistente.setSucursal(sucursal);
        inventarioExistente.setStockActual(50);
        inventarioExistente.setStockMin(10);
        inventarioExistente.setStockMax(100);
        inventarioExistente = inventarioRepository.save(inventarioExistente);
    }

    @Test
    void listarInventario_debeRetornarLista() throws Exception {
        mockMvc.perform(get("/inventario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stockActual").value(50));
    }

    @Test
    void crearInventario_conDatosValidos_debeRetornarCreado() throws Exception {
        Integer productoId = inventarioExistente.getProducto().getIdProducto();
        Integer sucursalId = inventarioExistente.getSucursal().getId();

        String nuevoInventarioJson = String.format("""
        {
            "producto": { "idProducto": %d },
            "sucursal": { "id": %d },
            "stockActual": 30,
            "stockMin": 5,
            "stockMax": 50
        }
        """, productoId, sucursalId);

        mockMvc.perform(post("/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoInventarioJson))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarInventario_conIdExistente_debeRetornarOk() throws Exception {
        String inventarioActualizadoJson = """
        {
            "stockActual": 60,
            "stockMin": 15,
            "stockMax": 120
        }
        """;

        mockMvc.perform(put("/inventario/{id}", inventarioExistente.getIdInventario())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inventarioActualizadoJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Inventario actualizado exitosamente"));
    }

    @Test
    void eliminarInventario_conIdExistente_debeRetornarOk() throws Exception {
        mockMvc.perform(delete("/inventario/{id}", inventarioExistente.getIdInventario()))
                .andExpect(status().isOk())
                .andExpect(content().string("Inventario eliminado exitosamente"));
    }
}