package tingeso.HU1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tingeso.HU1.entities.Ingreso_salidaEntity;
import tingeso.HU1.services.Ingreso_salidaService;

@RestController
@RequestMapping("/ingreso_salida") 
public class Ingreso_salidaController {
    @Autowired
    Ingreso_salidaService ingreso_salidaService;

    @GetMapping("/buscarInasistencias")
    public ResponseEntity<ArrayList<Ingreso_salidaEntity>> buscarInasistencias(){
      ArrayList<Ingreso_salidaEntity> inasistencias = ingreso_salidaService.buscarInasistencias();
        return ResponseEntity.ok(inasistencias);
    }
}
