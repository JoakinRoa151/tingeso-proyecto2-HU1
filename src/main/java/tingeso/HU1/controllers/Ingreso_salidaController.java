package tingeso.HU1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tingeso.HU1.services.Ingreso_salidaService;

@RestController
@RequestMapping 
public class Ingreso_salidaController {
    @Autowired
    Ingreso_salidaService ingreso_salidaService;

    @GetMapping("/leerArchivo")
    public String leerArchivo(){
        ingreso_salidaService.eliminarTodoIngresoSalida();
        ingreso_salidaService.LeerArchivo();
        return "redirect:/inasistenciasAutomatico";
    }

}
