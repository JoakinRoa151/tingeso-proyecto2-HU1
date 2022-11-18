package tingeso.HU1.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
    private String folder="cargas//";
    private final Logger logg = LoggerFactory.getLogger(UploadService.class);

    public String save(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                
                byte [] bytes= file.getBytes();
                Path path = Paths.get( folder+file.getOriginalFilename() );
                System.out.println(folder+file.getOriginalFilename());
                Files.write(path, bytes);
                logg.info("Archivo guardado");
                

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Archivo guardado correctamente";
    }


}