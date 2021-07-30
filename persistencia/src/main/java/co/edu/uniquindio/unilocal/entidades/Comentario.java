package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotBlank(message = "Debe ingresar un mensaje como comentario")
    @Size(min = 1, max = 255, message = "El mensaje debe tener entre 1 y 255 caracteres")
    private String mensaje;

    //puntuación dada
    @Column(name = "calificacion", nullable = false, precision = 1, scale = 1)
    @Positive(message = "La calificación debe ser positiva")
    @NotBlank(message = "Debe seleccionar una calificación valida")
    private Integer calificacion;

    //Respuesta dada al comentario
    @Column(name = "respuesta")
    private String respuesta;

    //Fecha en la que se realizo el comentario
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comentario", nullable = false)
    @NotBlank(message = "Debe ingresar la fecha en la que se realizo el comentario")
    private Date fechaComentario;

    //Usuario que realizo el comentario
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioComentario;

    //Lugar a el cúal se le realizo el comentario
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lugar lugarComentario;

    /**
     * Constructor completo para crear comentarios
     *
     * @param mensaje,         mensaje realizado en el comentario
     * @param calificacion,    puntuación dada en el comentario
     */
    public Comentario(String mensaje, @Positive Integer calificacion, Usuario usuarioComentario, Lugar lugarComentario) {
        this.mensaje = mensaje;
        this.calificacion = calificacion;
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
