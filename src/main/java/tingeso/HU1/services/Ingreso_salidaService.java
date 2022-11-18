package tingeso.HU1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.HU1.entities.Ingreso_salidaEntity;
import tingeso.HU1.repositories.Ingreso_salidaRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
@Service
public class Ingreso_salidaService {
    @Autowired
    Ingreso_salidaRepository ingreso_salidaRepository;

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
    public void LeerArchivo(){
        try {
            File myObj = new File("cargas/DATA.txt");
            Scanner scanner = new Scanner(myObj);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                StringTokenizer atributo= new StringTokenizer(linea, ";");
                Ingreso_salidaEntity ingreso_salida = new Ingreso_salidaEntity();
                while(atributo.hasMoreElements()){
                    Date fecha= convertidorDate(atributo.nextElement().toString());
                    ingreso_salida.setFecha(fecha);
                    Time hora = convertidorTime(atributo.nextElement().toString());
                    ingreso_salida.setHora(hora);
                    ingreso_salida.setRut_ing_sal(atributo.nextElement().toString());
                }
                guardarIngreso_salida(ingreso_salida);
            }
            scanner.close();
            return;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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

    public void eliminarTodoIngresoSalida(){
        ingreso_salidaRepository.deleteAll();
        return;
    }
}
