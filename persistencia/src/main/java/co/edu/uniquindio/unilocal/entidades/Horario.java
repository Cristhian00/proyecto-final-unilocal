package co.edu.uniquindio.unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos de un horario
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Horario implements Serializable {

    //Código de identificación de un horario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
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
     * Constructor vacio del horario
     */
    public Horario() {
        super();
    }

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
     * Método que obtiene el código de identificación del horario
     *
     * @return el código de identificación
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Método que modifica el código de identificación del horario
     *
     * @param codigo, número nuevo de identificación
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Método que obtiene el día de la semana del horario
     *
     * @return día de la semana
     */
    public DiaSemana getDia() {
        return dia;
    }

    /**
     * Método que modifica el día de la semana a la que pertene el horario
     *
     * @param dia, día de la semana
     */
    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    /**
     * Método que obtiene la hora de apertura en el horario
     *
     * @return hora de apertura
     */
    public String getHoraApertura() {
        return horaApertura;
    }

    /**
     * Método que modifica la hora de apertura
     *
     * @param horaApertura, hora de apertura nueva
     */
    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    /**
     * Método que obtiene la hora de cierre
     *
     * @return hora de cierre
     */
    public String getHoraCierre() {
        return horaCierre;
    }

    /**
     * Método que modifica la hora de cierre en el horario
     *
     * @param horaCierre, hora de cierre nueva
     */
    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    /**
     * Metodo que compara dos horarios
     *
     * @param o, horario a comparar
     * @return true si los horarios son el mismo, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;
        if(this.dia.compareTo(horario.dia) == 0 && this.horaApertura.equals(horario.horaApertura)
                && this.horaCierre.equals(horario.horaCierre)){
            return true;
        }

        return codigo == horario.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
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
