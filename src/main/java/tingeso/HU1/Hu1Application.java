package tingeso.HU1;

import javax.annotation.Resource;

import org.hibernate.graph.internal.SubGraphImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tingeso.HU1.services.FileUploadService;

@SpringBootApplication
public class Hu1Application implements CommandLineRunner {
	@Resource
	FileUploadService fileUploadService;
	public static void main(String[] args) {
		SpringApplication.run(Hu1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		fileUploadService.deleteDirectory();
		fileUploadService.init();		
	}
}
