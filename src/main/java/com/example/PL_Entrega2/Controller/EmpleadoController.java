package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Model.Empleado;
import com.example.PL_Entrega2.Service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Empleados API", description = "Servicio de gestión para empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Operation(
            summary = "Listar empleados",
            description = "Obtiene todos los empleados registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de empleados"),
                    @ApiResponse(responseCode = "204", description = "No hay empleados registrados")
            }
    )

    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @Operation(
            summary = "Obtener empleado por ID",
            description = "Busca un empleado específico por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleado(@PathVariable Integer id) {
        Optional<Empleado> empleado = empleadoService.getEmpleado(id);

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: no existe el empleado");
        }
    }

    @Operation(
            summary = "Crear empleado",
            description = "Registra un nuevo empleado",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empleado creado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )

    @PostMapping
    public ResponseEntity<String> crearEmpleado(@RequestBody Empleado empleado) {
        empleadoService.addEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Empleado creado exitosamente");
    }

    @Operation(
            summary = "Actualizar empleado",
            description = "Actualiza un empleado ya existente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empleado actualizado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEmpleado(
            @PathVariable Integer id,
            @RequestBody Empleado empleado) {
        if (empleadoService.updateEmpleado(id, empleado)) {
            return ResponseEntity.ok("Empleado actualizado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se pudo actualizar. Empleado con ID " + id + " no existe");
    }

    @Operation(
            summary = "Eliminar empleado",
            description = "Elimina un empleado registrado",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empleado eliminado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Integer id) {
        if (empleadoService.deleteEmpleado(id)) {
            return ResponseEntity.ok("Empleado eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se pudo eliminar. Empleado con ID " + id + " no existe");
    }
}