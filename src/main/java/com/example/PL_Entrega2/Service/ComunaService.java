package com.example.PL_Entrega2.Service;


import com.example.PL_Entrega2.Model.Comuna;
import com.example.PL_Entrega2.Repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComunaService {
    @Autowired
    ComunaRepository comunaRepository;

    public String getAllComunas(){
        String output = "";
        for(Comuna comuna : comunaRepository.findAll()){
            output += "ID Comuna: " + comuna.getIdComuna() + "\n";
            output += "Nombre Comuna: " + comuna.getNombreComuna() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontraron comunas";
        }
        else{
            return output;
        }
    }

    public String getComuna(int id){
        String output = "";
        if(comunaRepository.existsById(id)){
            Comuna comuna = comunaRepository.findById(id).get();
            output += "ID Comuna: " + comuna.getIdComuna() + "\n";
            output += "Nombre Comuna: " + comuna.getNombreComuna() + "\n";
            return output;
        }
        else{
            return "No se encontro la comuna";
        }
    }

    public String addComuna(Comuna comuna){
        comunaRepository.save(comuna);
        return "Comuna agregada";
    }

    public String deleteComuna(int id){
        if(comunaRepository.existsById(id)){
            comunaRepository.deleteById(id);
            return "Comuna eliminada";
        }
        else{
            return "No se encontro la comuna";
        }
    }

    public String updateComuna(int id, Comuna comuna){
        if(comunaRepository.existsById(id)){
            Comuna buscado = comunaRepository.findById(id).get();
            buscado.setNombreComuna(comuna.getNombreComuna());
            buscado.setIdComuna(id);
            comunaRepository.save(buscado);
            return "Comuna actualizada";
        }
        else{
            return "No se encontro la comuna";
        }
    }

}
