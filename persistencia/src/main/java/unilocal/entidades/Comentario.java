package unilocal.entidades;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "caliicacion", nullable = false, precision = 1, scale = 1)
    @Positive
    private double calificacion;

    @Column(name = "respuesta", nullable = false)
    private String respuesta;

    @Temporal(TemporalType.TIME)
    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;

    @ManyToOne
    private Usuario usuarioComentario;

    @ManyToOne
    private Lugar lugarComentario;

    public Comentario(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

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
}
