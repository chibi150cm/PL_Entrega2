package com.example.PL_Entrega2.Controller;


import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunas")

public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public String ListaComunas(){return comunaService.getAllComunas();}

    @GetMapping("/{id}") String ObtenerComuna(@PathVariable int id){return comunaService.getComuna(id);}

    @PostMapping
    public String crearComuna(@RequestBody Comuna comuna){return comunaService.addComuna(comuna);}

    @PutMapping("/{id}")
    public String actualizarComuna(@PathVariable int id, @RequestBody Comuna comuna){return comunaService.updateComuna(id, comuna);}

    @DeleteMapping("/{id}")
    public String eliminarComuna(@PathVariable int id){return comunaService.deleteComuna(id);}

}
