package unilocal.entidades;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase es la que contiene los atributos de los comentarios y sus respectivos métodos individuales
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Comentario implements Serializable {

    //Número de identificación de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
     * Constructor vacio
     */
    public Comentario(){
        super();
    }

    /**
     * Constructor completo para crear comentarios
     * @param mensaje, mensaje realizado en el comentario
     * @param calificacion, puntuación dada en el comentario
     * @param fechaComentario, fecha en la que se realizo el comentario
     */
    public Comentario(String mensaje, @Positive double calificacion, Date fechaComentario) {
        this.mensaje = mensaje;
        this.calificacion = calificacion;
        this.fechaComentario = fechaComentario;
    }

    /**
     * Método que obtiene el valor del id del comentario
     * @return número de id del comentario
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el número de id del comentario
     * @param id, número id nuevo a cambiar del comentario
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que obtiene el mensaje del comentario
     * @return mensaje escrito en el comentario
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Método que modifica el mensaje del comentario
     * @param mensaje, nuevo mensaje para el comentario
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Método que obtiene la calificación del comentario
     * @return la calificación del comentario
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * Método que modifica la calificación del comentario
     * @param calificacion, nuevo puntaje del comentario
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Método que obtiene el mensaje de la respuesta del comentario
     * @return el mensaje de la respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Método que modifica el mensaje de la respuesta
     * @param respuesta, mensaje nuevo que va a tener la respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Método que obtiene la fecha en la que se realizo el comentario
     * @return la fecha del comentario
     */
    public Date getFechaComentario() {
        return fechaComentario;
    }

    /**
     * Método que modifica la fecha en la que se realizo el comentario
     * @param fechaComentario, fecha nueva que va a tener el comentario
     */
    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    /**
     * Método que obtiene el usuario que realizo el comentario
     * @return el usuario
     */
    public Usuario getUsuarioComentario() {
        return usuarioComentario;
    }

    /**
     * Método que modifica el usuario que realizo el comentario
     * @param usuarioComentario, el nuevo usuario
     */
    public void setUsuarioComentario(Usuario usuarioComentario) {
        this.usuarioComentario = usuarioComentario;
    }

    /**
     * Método que obtiene el lugar al cúal se realizo el comentario
     * @return lugar en que se realizo el comentario
     */
    public Lugar getLugarComentario() {
        return lugarComentario;
    }

    /**
     * Método que modifica el lugar al cúal se realizo el comentario
     * @param lugarComentario, lugar nuevo que contendrá el comentario
     */
    public void setLugarComentario(Lugar lugarComentario) {
        this.lugarComentario = lugarComentario;
    }

    /**
     * Método que verifica si un comentario es igual a otro
     * @param o, comentario a comparar
     * @return true si los comentarios son el mismo, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comentario that = (Comentario) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Método que muestra los datos que contiene un comentario
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
