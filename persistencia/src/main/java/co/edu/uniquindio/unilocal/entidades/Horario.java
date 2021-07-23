package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "dia", nullable = false)
    private DiaSemana dia;

    //Hora de inicio o apertura
    @Column(name = "hora_apertura", length = 10, nullable = false)
    private String horaApertura;

    //Hora final o cierre
    @Column(name = "hora_cierre", length = 10, nullable = false)
    private String horaCierre;

    //Lista de lugares que tienen el horario
    @ManyToMany(mappedBy = "horarios")
    private List<Lugar> lugares;

    /**
     * Constructor completo para crear un horario
     *
     * @param dia,          día al que pertenece el horario
     * @param horaApertura, hora de inicio del horario
     * @param horaCierre,   hora de cierre del horario
     */
    public Horario(DiaSemana dia, String horaApertura, String horaCierre) {
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
