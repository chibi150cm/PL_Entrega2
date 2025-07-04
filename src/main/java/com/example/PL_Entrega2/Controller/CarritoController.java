package com.example.PL_Entrega2.Controller;


import com.example.PL_Entrega2.Assembler.CarritoModelAssembler;
import com.example.PL_Entrega2.Model.Carrito;
import com.example.PL_Entrega2.Service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
Me arrepiento de haber intentado aprender como utilizar el objeto completo en CarritoService, tomo mucho tiempo hacerlo
funcionar. Aunque si ahora en teoria la mantencion del codigo es menor. Si se quiere añadir mas variables a carrito no es
necesario ir metodo por metodo añadiendo tales variables.

A su vez cambie las respuestas que dan a controlador, ya que es ahi en teoria donde van, ya que maneja las solicitudes HTTP
entrantes y devuelve las respuestas HTTP
 */

@RestController
@RequestMapping("/carritos")
@Tag(name="Controlador Carritos", description = "Servio de gestion de carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    CarritoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener carritos", description = "Obtiene la lista completa de carritos en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busqueda de carrito en el sistema sgunn su ID"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    
    public ResponseEntity<?> getAllCarritos() {
        List<Carrito> carritos = carritoService.getAllCarritos();
        if (carritos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO HAY CARRITOS");
        }
        return ResponseEntity.ok(carritos);
    }


    //Por algun motivo es necesario especificar que es un objeto, <?> no funciona.Algo que ver con que orElse espera el mismo tipo de valor :C.
    @GetMapping("/{id}")
    @Operation(summary = "Busqueda de carrito",description = "Busqueda de carrito en el sistema según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito encontrado"),
            @ApiResponse(responseCode = "404", description = "Carrito no existente")
    })
    @Parameter(description = "El ID del carrito", example = "123")
    public ResponseEntity<Object> getCarritoById(@PathVariable int id) {
        return carritoService.getCarritoById(id)
                .map(carrito -> ResponseEntity.ok().body((Object) carrito))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body((Object) "No hay carritos con ese ID"));
    }

    @PostMapping
    @Operation(summary = "Agregar Carrito", description = "Permite registrar un carrito en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Carrito.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<?> addCarrito(@RequestBody Carrito carrito) {
        carritoService.addCarrito(carrito);
        if (carritoService.getCarritoById(carrito.getIdCarrito()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(carrito), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Carrito por ID", description = "Elimina un carrito según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Carrito"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del carrito", example = "123")
    public ResponseEntity<Void> deleteCarritoById(@PathVariable int id) {
        if (carritoService.getCarritoById(id).isPresent()) {
            carritoService.deleteCarrito(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
