package tingeso.HU1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso.HU1.services.UploadService;

@RestController
public class HomeController {

    @Autowired
    UploadService upload;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/subirArchivo")
    public String home() {
        return "home";
    }

    @PostMapping("/cargar")
    public String carga( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) {
        if(file.getOriginalFilename().equals("DATA.txt")){
            upload.save(file);
            ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
            return "redirect:/leerArchivo";
        }
        else{
            ms.addFlashAttribute("mensaje", "Archivo Incorrecto!, el archivo debe llamarse DATA.txt!");
            return "redirect:/subirArchivo";
        }
    }

}