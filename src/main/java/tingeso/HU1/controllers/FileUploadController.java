package tingeso.HU1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tingeso.HU1.services.FileUploadService;
import tingeso.HU1.services.Ingreso_salidaService;

@Controller
@RequestMapping("/file")
@CrossOrigin("http://localhost:3000")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    Ingreso_salidaService Ingreso_salidaService;

    @RequestMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try{
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
