package tingeso.HU1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "ingreso_salida")
@Data
@AllArgsConstructor
public class Ingreso_salidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date fecha;
    private Time hora;
    private String rut_ing_sal;

    public Ingreso_salidaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getRut_ing_sal() {
        return rut_ing_sal;
    }

    public void setRut_ing_sal(String rut_ing_sal) {
        this.rut_ing_sal = rut_ing_sal;
    }
}


