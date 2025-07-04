package com.example.PL_Entrega2.Controller;


import com.example.PL_Entrega2.Assembler.ComunaModelAssembler;
import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Service.ComunaService;
import com.example.PL_Entrega2.Service.ComunaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunas")
@Tag(name="Controlador Comuna", description = "Servio de gestion de comuna")
public class ComunaController {
    @Autowired
    ComunaService comunaService;

    @Autowired
    ComunaModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Comunas", description = "Obtiene la lista completa de comunas registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de comunas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Comuna>>> getAllComunas() {
        List<Comuna> lista = comunaService.findAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Comuna por ID", description = "Obtiene un comuna segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Comuna"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del comuna", example = "123")
    public ResponseEntity<EntityModel<Comuna>> getComunaById(@PathVariable int id) {
        if (comunaService.getComuna(id).isPresent()) {
            Comuna comuna = comunaService.getComuna(id).get();
            return new ResponseEntity<>(assembler.toModel(comuna), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Comuna", description = "Permite registrar un comuna en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comuna creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comuna.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Comuna>> addComuna(@RequestBody Comuna comuna) {
        comunaService.addComuna(comuna);
        if (comunaService.getComuna(comuna.getIdComuna()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(comuna), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Comuna por ID", description = "Elimina un comuna segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Comuna"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del comuna", example = "123")
    public ResponseEntity<Void> deleteComunaById(@PathVariable int id) {
        if (comunaService.getComuna(id).isPresent()) {
            comunaService.deleteComuna(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Comuna", description = "Permite actualizar los datos de un comuna segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comuna modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comuna.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del comuna", example = "123")
    public ResponseEntity<EntityModel<Comuna>> updateComuna(@PathVariable int id, @RequestBody Comuna comuna) {
        if (comunaService.getComuna(id).isPresent()) {
            comunaService.updateComuna(id, comuna);
            return new ResponseEntity<>(assembler.toModel(comuna), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
