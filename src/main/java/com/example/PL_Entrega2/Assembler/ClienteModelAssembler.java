package com.example.PL_Entrega2.Assembler;


import com.example.PL_Entrega2.Controller.ClienteController;
import com.example.PL_Entrega2.Model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).getClienteById(cliente.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("clientes")

        );
    }
}
