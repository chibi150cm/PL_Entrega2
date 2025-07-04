package com.example.PL_Entrega2.Controller;

import com.example.PL_Entrega2.Assembler.ClienteModelAssembler;
import com.example.PL_Entrega2.Model.Cliente;
import com.example.PL_Entrega2.Service.ClienteService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name="Controlador Clientes", description = "Servio de gestion de clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Clientes", description = "Obtiene la lista completa de clientes registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de clientes"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> getAllClientes() {
        List<Cliente> lista = clienteService.findAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente por ID", description = "Obtiene un cliente segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Cliente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cliente", example = "123")
    public ResponseEntity<EntityModel<Cliente>> getClienteById(@PathVariable int id) {
        if (clienteService.getCliente(id).isPresent()) {
            Cliente cliente = clienteService.getCliente(id).get();
            return new ResponseEntity<>(assembler.toModel(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Cliente", description = "Permite registrar un cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Cliente>> addCliente(@RequestBody Cliente cliente) {
        clienteService.addCliente(cliente);
        if (clienteService.getCliente(cliente.getIdCliente()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(cliente), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cliente por ID", description = "Elimina un cliente segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Cliente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cliente", example = "123")
    public ResponseEntity<Void> deleteClienteById(@PathVariable int id) {
        if (clienteService.getCliente(id).isPresent()) {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Cliente", description = "Permite actualizar los datos de un cliente segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del cliente", example = "123")
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        if (clienteService.getCliente(id).isPresent()) {
            clienteService.updateCliente(id, cliente);
            return new ResponseEntity<>(assembler.toModel(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
