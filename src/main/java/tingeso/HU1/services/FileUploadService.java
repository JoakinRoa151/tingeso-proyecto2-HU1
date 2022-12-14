package tingeso.HU1.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    private final Path root = Paths.get("uploads");

    public void init(){
        try{
            Files.createDirectory(root);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteDirectory(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public void save(MultipartFile file){
        try{
            Files.copy(
                file.getInputStream(),
                root.resolve(file.getOriginalFilename())
            );
        }
        catch(Exception e){
            throw new RuntimeException("Archivo existe en " + e.getMessage());
        }
    }
    
    public Resource load(String filename){
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo");
            }
        }
        catch(Exception e){
            throw new RuntimeException("Archivo no existe " + e.getMessage());
        }
    }
}
