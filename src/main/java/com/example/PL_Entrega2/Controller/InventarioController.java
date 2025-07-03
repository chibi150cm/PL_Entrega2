package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Inventario;
import com.example.PL_Entrega2.Service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@Tag(name = "Inventario API", description = "Gestión de stock y relaciones entre productos y sucursales")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Operation(
            summary = "Listar todo el inventario",
            description = "Obtiene todos los registros de inventario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de inventario obtenida"),
                    @ApiResponse(responseCode = "204", description = "No hay registros de inventario")
            }
    )

    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        List<Inventario> inventarios = inventarioService.getAllInventario();
        return ResponseEntity.ok(inventarios);
    }

    @Operation(
            summary = "Obtener registro de inventario por ID",
            description = "Busca un registro específico en el inventario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro encontrado"),
                    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInventario(@PathVariable Integer id) {
        return inventarioService.getInventario(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: No existe el inventario"));
    }

    @Operation(
            summary = "Agregar registro al inventario",
            description = "Crea una nueva relación producto-sucursal con stock",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Registro creado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Producto o sucursal no encontrados")
            }
    )

    @PostMapping
    public ResponseEntity<String> agregarInventario(@RequestBody Inventario inventario) {
        inventarioService.addInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Inventario creado exitosamente");
    }

    @Operation(
            summary = "Actualizar stock en inventario",
            description = "Modifica los niveles de stock para un producto en una sucursal",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock actualizado"),
                    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarInventario(
            @PathVariable Integer id,
            @RequestBody Inventario inventario) {
        if (inventarioService.updateInventario(id, inventario)) {
            return ResponseEntity.ok("Inventario actualizado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se pudo actualizar. Inventario con ID " + id + " no existe");
    }

    @Operation(
            summary = "Eliminar registro de inventario",
            description = "Elimina una relación producto-sucursal del inventario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro eliminado"),
                    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Integer id) {
        if (inventarioService.deleteInventario(id)) {
            return ResponseEntity.ok("Inventario eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se pudo eliminar. Inventario con ID " + id + " no existe");
    }
}