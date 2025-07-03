package com.example.PL_Entrega2.Assembler;

import com.example.PL_Entrega2.Controller.ProductoController;
import com.example.PL_Entrega2.Model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class)
                        .obtenerProducto(producto.getIdProducto())).withSelfRel()
                        .andAffordance(afford(methodOn(ProductoController.class)
                                .actualizarProducto(producto.getIdProducto(), null)))
                        .andAffordance(afford(methodOn(ProductoController.class)
                                .eliminarProducto(producto.getIdProducto()))),
                linkTo(methodOn(ProductoController.class)
                        .listarProductos()).withRel("productos"),
                linkTo(methodOn(ProductoController.class)
                        .crearProducto(null)).withRel("create")
        );
    }
}