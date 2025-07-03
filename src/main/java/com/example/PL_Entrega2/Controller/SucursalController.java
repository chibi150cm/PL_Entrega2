package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Sucursal;
import com.example.PL_Entrega2.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sucursales")
@Tag(name = "Sucursales API", description = "Servicio de gestión de sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Operation(
            summary = "Listar sucursales",
            description = "Obtiene todas las sucursales registradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida"),
                    @ApiResponse(responseCode = "204", description = "No hay sucursales registradas")
            }
    )
    @GetMapping
    public ResponseEntity<List<Sucursal>> listarSucursales() {
        return ResponseEntity.ok(sucursalService.getAllSucursales());
    }

    @Operation(
            summary = "Obtener sucursal por ID",
            description = "Busca una sucursal específica por su id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
                    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSucursal(@PathVariable Integer id) {
        Optional<Sucursal> sucursal = sucursalService.getSucursal(id);
        if (sucursal.isPresent()) {
            return ResponseEntity.ok(sucursal.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: Sucursal con ID " + id + " no existe");
    }

    @Operation(
            summary = "Crear sucursal",
            description = "Registra una nueva sucursal en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            }
    )

    @PostMapping
    public ResponseEntity<String> crearSucursal(@RequestBody Sucursal sucursal) {
        sucursalService.addSucursal(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Sucursal creada exitosamente");
    }

    @Operation(
            summary = "Actualizar sucursal",
            description = "Modifica los datos de una sucursal existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucursal actualizada"),
                    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarSucursal(
            @PathVariable Integer id,
            @RequestBody Sucursal sucursal) {
        if (sucursalService.updateSucursal(id, sucursal)) {
            return ResponseEntity.ok("Sucursal actualizada exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: La sucursal no existe");
    }

    @Operation(
            summary = "Eliminar sucursal",
            description = "Remueve una sucursal del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucursal eliminada"),
                    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSucursal(@PathVariable Integer id) {
        if (sucursalService.deleteSucursal(id)) {
            return ResponseEntity.ok("Sucursal eliminada exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: La sucursal con Id: " + id +" no existe");
    }
}
