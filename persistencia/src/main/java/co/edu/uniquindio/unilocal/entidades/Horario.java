package co.edu.uniquindio.unilocal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos de un horario
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Horario implements Serializable {

    //Código de identificación de un horario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    @EqualsAndHashCode.Include
    private int codigo;

    //Día de la semana a la que pertenece el horario
    @Column(name = "dia", nullable = false, length = 100)
    @NotBlank(message = "Debe escribir un día para el horario")
    @Size(min = 5, max = 100, message = "El día debe tener entre 5 y 100 caracteres")
    private String dia;

    //Hora de inicio o apertura
    @Column(name = "hora_apertura", length = 5, nullable = false)
    @NotBlank(message = "Debe seleccionar hora una de apertura")
    @Size(min = 5, max = 5, message = "La hora de apertura debe tener 5 caracteres")
    @Pattern(regexp = "\\d{2}\\:\\d{2}", message = "Debe ingresar un formato valido de hora. Ejemplo: 09:00")
    private String horaApertura;

    //Hora final o cierre
    @Column(name = "hora_cierre", length = 5, nullable = false)
    @NotBlank(message = "Debe seleccionar hora una de cierre")
    @Size(min = 5, max = 5, message = "La hora de cierre debe tener 5 caracteres")
    @Pattern(regexp = "\\d{2}\\:\\d{2}", message = "Debe ingresar un formato valido de hora. Ejemplo: 09:00")
    private String horaCierre;

    //Lugar que posee el horario
    @ManyToOne
    @JsonIgnore
    private Lugar lugarHorario;

    /**
     * Constructor completo para crear un horario
     *
     * @param dia,          día al que pertenece el horario
     * @param horaApertura, hora de inicio del horario
     * @param horaCierre,   hora de cierre del horario
     */
    public Horario(String dia, String horaApertura, String horaCierre) {
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    /**
     * Método que muestra los datos que tiene el horario
     *
     * @return una cadena con los datos del horario
     */
    @Override
    public String toString() {
        return "Horario{" +
                "codigo=" + codigo +
                ", dia=" + dia +
                ", horaApertura='" + horaApertura + '\'' +
                ", horaCierre='" + horaCierre +
                '}';
    }
}
