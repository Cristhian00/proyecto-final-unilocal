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

    @Column(name = "calificacion", nullable = false, precision = 1, scale = 1)
    @Positive
    private double calificacion;

    @Column(name = "respuesta")
    private String respuesta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;

    @ManyToOne
    private Usuario usuarioComentario;

    @ManyToOne
    private Lugar lugarComentario;

    public Comentario(){
        super();
    }

    public Comentario(String comentario, @Positive double calificacion, Date fechaComentario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fechaComentario = fechaComentario;
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

    public Usuario getUsuarioComentario() {
        return usuarioComentario;
    }

    public void setUsuarioComentario(Usuario usuarioComentario) {
        this.usuarioComentario = usuarioComentario;
    }

    public Lugar getLugarComentario() {
        return lugarComentario;
    }

    public void setLugarComentario(Lugar lugarComentario) {
        this.lugarComentario = lugarComentario;
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

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", calificacion=" + calificacion +
                ", respuesta='" + respuesta + '\'' +
                ", fechaComentario=" + fechaComentario +
                '}';
    }
}
