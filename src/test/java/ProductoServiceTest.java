import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Repository.ProductoRepository;
import com.example.PL_Entrega2.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void obtenerProducto_debeRetornarProductoCuandoExiste() {
        Producto producto = new Producto();
        producto.setIdProducto(1);
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.getProducto(1);

        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getIdProducto());
    }

    @Test
    void eliminarProducto_debeRetornarFalseCuandoNoExiste() {
        when(productoRepository.existsById(99)).thenReturn(false);
        assertFalse(productoService.deleteProducto(99));
    }
}