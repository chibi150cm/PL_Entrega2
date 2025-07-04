package com.example.PL_Entrega2.Assembler;

import com.example.PL_Entrega2.Controller.CarritoController;
import com.example.PL_Entrega2.Model.Carrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {



    @Override
    public EntityModel <Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoController.class).getCarritoById(carrito.getIdCarrito())).withSelfRel(),
                linkTo(methodOn(CarritoController.class).getAllCarritos()).withRel("carritos")
                        );
    }
}
