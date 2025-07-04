package com.example.PL_Entrega2.Assembler;


import com.example.PL_Entrega2.Controller.ComunaController;
import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Model.Comuna;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComunaModelAssembler implements RepresentationModelAssembler<Comuna, EntityModel<Comuna>> {
    
    @Override
    public EntityModel<Comuna> toModel(Comuna comuna) {
        return EntityModel.of(comuna,
                linkTo(methodOn(ComunaController.class).getComunaById(comuna.getIdComuna())).withSelfRel(),
                linkTo(methodOn(ComunaController.class).getAllComunas()).withRel("comunas")

        );
    }
    
}
