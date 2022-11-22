package tingeso.HU1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import tingeso.HU1.services.FileUploadService;
import tingeso.HU1.services.Ingreso_salidaService;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    Ingreso_salidaService Ingreso_salidaService;
    

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try{
            String respuesta = restTemplate.getForObject("http://HU2-service/inasistencia/eliminarInasistencias", String.class);
            String respuesta2 = restTemplate.getForObject("http://HU3-service/autorizacion/eliminarAutorizaciones", String.class);
            String fileName = file.getOriginalFilename();
            fileUploadService.save(file);
            Ingreso_salidaService.eliminarTodoIngreso_salida();
            Ingreso_salidaService.leerArchivoSubido(fileName);
            return ResponseEntity.ok().body("Archivo subido correctamente");
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } 
}
