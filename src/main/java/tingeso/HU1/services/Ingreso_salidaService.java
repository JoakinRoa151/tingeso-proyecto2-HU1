package tingeso.HU1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.HU1.entities.Ingreso_salidaEntity;
import tingeso.HU1.repositories.Ingreso_salidaRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import org.springframework.core.io.Resource;
@Service
public class Ingreso_salidaService {
    @Autowired
    Ingreso_salidaRepository ingreso_salidaRepository;

    @Autowired
    FileUploadService fileUploadService;


    public Ingreso_salidaEntity guardarIngreso_salida(Ingreso_salidaEntity ingreso_salida){
        return ingreso_salidaRepository.save(ingreso_salida);
    }
    public Time convertidorTime(String hora) throws ParseException {
        Time nueva_hora = new Time((new SimpleDateFormat("HH:mm").parse(hora)).getTime());
        return nueva_hora;
    }

    public Date convertidorDate(String fecha) throws ParseException{
        Date nueva_fecha = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(fecha).getTime());
        return nueva_fecha;
    }
    
    public ArrayList<Ingreso_salidaEntity> horasExtraPorRut(String rutEmpleado){
        return (ingreso_salidaRepository.buscarHorasExtrasPorRut(rutEmpleado));
    }

    public double cantidadHorasExtraPorRut(String rutEmpleado){
        ArrayList<Ingreso_salidaEntity> horasExtraEmpleado= ingreso_salidaRepository.buscarHorasExtrasPorRut(rutEmpleado);
        double contadorHoras= 0;
        for(Ingreso_salidaEntity horaExtra : horasExtraEmpleado){
            contadorHoras=(horaExtra.getHora().getHours()-18)+ contadorHoras;
        }
        return(contadorHoras);
    }
    public ArrayList<Ingreso_salidaEntity> buscarInasistencias(){
        return(ingreso_salidaRepository.buscarInasistencias());
    }
    public ArrayList<String> buscarEmpleadosHorasExtra(){
        return(ingreso_salidaRepository.buscarEmpleadosHorasExtra());
    }

    public void eliminarTodoIngreso_salida(){
        ingreso_salidaRepository.deleteAll();
        return;
    }

    private Ingreso_salidaEntity lineaAIngreso_salida(String linea) throws ParseException{
        // Se separa la linea por los ;
        String[] lineaSeparada =  linea.split(";");

        Date fecha = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(lineaSeparada[0]).getTime());
        Time hora  = new Time((new SimpleDateFormat("HH:mm").parse(lineaSeparada[1])).getTime());
        String rut = lineaSeparada[2];

        Ingreso_salidaEntity Ingreso_salidaEntity = new Ingreso_salidaEntity(null, fecha, hora, rut);
        return Ingreso_salidaEntity;
    }


    public void leerArchivoSubido(String filename){
        try{
            Resource data = fileUploadService.load(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(data.getInputStream()));
            String line = reader.readLine();
            while(line != null){
                ingreso_salidaRepository.save(lineaAIngreso_salida(line));
                line = reader.readLine();
            }
            reader.close();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
