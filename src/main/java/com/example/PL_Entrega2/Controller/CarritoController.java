package com.example.PL_Entrega2.Controller;


import com.example.PL_Entrega2.Model.Carrito;
import com.example.PL_Entrega2.Service.CarritoService;
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
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<?> getAllCarritos() {
        List<Carrito> carritos = carritoService.getAllCarritos();
        if (carritos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO HAY CARRITOS");
        }
        return ResponseEntity.ok(carritos);
    }


    //Por algun motivo es necesario especificar que es un objeto, <?> no funciona.Algo que ver con que orElse espera el mismo tipo de valor :C.
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarritoById(@PathVariable int id) {
        return carritoService.getCarritoById(id)
                .map(carrito -> ResponseEntity.ok().body((Object) carrito))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body((Object) "No hay carritos con ese ID"));
    }

    @PostMapping
    public ResponseEntity<?> addCarrito(@RequestBody Carrito carrito) {
        carritoService.addCarrito(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carrito Añadido");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrito(@PathVariable int id) {
        if(carritoService.deleteCarrito(id)){
            return ResponseEntity.ok("Carrito eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado");
        }
    }
}
