package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase es la que contiene los atributos de los comentarios y sus respectivos métodos individuales
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Comentario implements Serializable {

    //Número de identificación de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    //Mensaje realizado por un usuario
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    //puntuación dada
    @Column(name = "calificacion", nullable = false, precision = 1, scale = 1)
    @Positive
    private double calificacion;

    //Respuesta dada al comentario
    @Column(name = "respuesta")
    private String respuesta;

    //Fecha en la que se realizo el comentario
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;

    //Usuario que realizo el comentario
    @ManyToOne
    private Usuario usuarioComentario;

    //Lugar a el cúal se le realizo el comentario
    @ManyToOne
    private Lugar lugarComentario;

    /**
     * Constructor completo para crear comentarios
     *
     * @param mensaje,         mensaje realizado en el comentario
     * @param calificacion,    puntuación dada en el comentario
     * @param fechaComentario, fecha en la que se realizo el comentario
     */
    public Comentario(String mensaje, @Positive double calificacion, Date fechaComentario, Usuario usuarioComentario, Lugar lugarComentario) {
        this.mensaje = mensaje;
        this.calificacion = calificacion;
        this.fechaComentario = fechaComentario;
        this.usuarioComentario = usuarioComentario;
        this.lugarComentario = lugarComentario;
    }

    /**
     * Método que muestra los datos que contiene un comentario
     *
     * @return una cadena con los datos del comentario
     */
    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", calificacion=" + calificacion +
                ", respuesta='" + respuesta + '\'' +
                ", fechaComentario=" + fechaComentario +
                '}';
    }
}
