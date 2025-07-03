package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Producto;
import com.example.PL_Entrega2.Service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(
        name = "Productos API",
        description = "Gestión completa del catálogo de productos"
)
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(
            summary = "Listar productos",
            description = "Obtiene todos los productos disponibles en el sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida"),
                    @ApiResponse(responseCode = "204", description = "No hay productos registrados")
            }
    )
    @GetMapping
    public ResponseEntity<?> listarProductos() {
        List<Producto> productos = productoService.getAllProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @Operation(
            summary = "Obtener producto por ID",
            description = "Recupera un producto específico por su identificador único",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerProducto(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Integer id) {
        return productoService.getProducto(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: No se encontró el producto con ID " + id));
    }

    @Operation(
            summary = "Crear nuevo producto",
            description = "Registra un nuevo producto en el catálogo del sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos del producto inválidos")
            }
    )
    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        productoService.addProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente");
    }

    @Operation(
            summary = "Actualizar producto",
            description = "Modifica los datos de un producto existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(
            @Parameter(description = "ID del producto a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @RequestBody Producto producto) {
        if (productoService.updateProducto(id, producto)) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: Producto con ID " + id + " no encontrado");
    }

    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto eliminado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(
            @Parameter(description = "ID del producto a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        if (productoService.deleteProducto(id)) {
            return ResponseEntity.ok("Producto eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: Producto con ID " + id + " no encontrado");
    }
}