package tingeso.HU1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "ingreso_salida")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingreso_salidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Date fecha;
    private Time hora;
    private String rut_ing_sal;
}



