package tingeso.HU1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/cantidadHorasExtraPorRut/{rutEmpleado}")
    public ResponseEntity<Double> cantidadHorasExtraPorRut(@PathVariable String rutEmpleado){
        double horasExtra = ingreso_salidaService.cantidadHorasExtraPorRut(rutEmpleado);
        return ResponseEntity.ok(horasExtra);
    }
    @GetMapping("/buscarAtrasosPorRutTipo1/{rutEmpleado}")
    public ResponseEntity<Integer> buscarAtrasosPorRutTipo1(@PathVariable String rutEmpleado){
        int atrasos = ingreso_salidaService.buscarAtrasosPorRutTipo1(rutEmpleado);
        return ResponseEntity.ok(atrasos);
    }
    @GetMapping("/buscarAtrasosPorRutTipo2/{rutEmpleado}")
    public ResponseEntity<Integer> buscarAtrasosPorRutTipo2(@PathVariable String rutEmpleado){
        int atrasos = ingreso_salidaService.buscarAtrasosPorRutTipo2(rutEmpleado);
        return ResponseEntity.ok(atrasos);
    }
    @GetMapping("/buscarAtrasosPorRutTipo3/{rutEmpleado}")
    public ResponseEntity<Integer> buscarAtrasosPorRutTipo3(@PathVariable String rutEmpleado){
        int atrasos = ingreso_salidaService.buscarAtrasosPorRutTipo3(rutEmpleado);
        return ResponseEntity.ok(atrasos);
    }
}
