package co.edu.uniquindio.unilocal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Clase que contiene todos los atributos de un lugar y sus métodos unitarios
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lugar implements Serializable {

    //Número de identificación del lugar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;

    //Nombre que tiene el lugar
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    @NotBlank(message = "Debe ingresar el nombre del lugar")
    @Size(min = 3, max = 100, message = "El nombre del lugar debe tener entre 3 y 100 caracteres")
    private String nombre;

    //Descripción del lugar
    @Column(name = "descripcion", nullable = false)
    @NotBlank(message = "Debe ingresar una descripción del lugar")
    @Size(min = 20, max = 255, message = "La descripción del lugar debe tener entre 20 y 255 caracteres")
    private String descripcion;

    //Tipo al que pertenece el lugar
    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoLugar tipoLugar;

    //Ciudad en la cuál esta el lugar
    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadLugar;

    //Fecha de creación del luagr en la plataforma
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    //Fecha en la que se aprobó el lugar
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;

    //Latitud de la ubicación del lugar
    @Column(name = "latitud", nullable = false, unique = true)
    private Float latitud;

    //Longitud de la ubicación del lugar
    @Column(name = "longitud", nullable = false, unique = true)
    private Float longitud;

    //Estado en el que se encuentra el lugar
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAprobacion estado;

    //Números telefonicos con los que cuenta el lugar
    @ElementCollection
    @JoinColumn(name = "telefono", nullable = false)
    //@NotEmpty(message = "Debe ingresar al menos un número de contacto")
    private Map<String, String> telefono;

    //Lista de horarios que tiene el lugar
    @OneToMany(mappedBy = "lugarHorario")
    @JsonIgnore
    private List<Horario> horarios;

    //Moderador que evaluo al lugar
    @ManyToOne
    private Moderador moderador;

    //Usuario que registro el lugar
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioCreador;

    //Usuarios que han seleccionado al lugar como favorito
    @ManyToMany(mappedBy = "lugaresFavoritos")
    @JsonIgnore
    private List<Usuario> usuariosFavoritos;

    //Comentarios que se le han realizado al lugar
    @OneToMany(mappedBy = "lugarComentario")
    @JsonIgnore
    private List<Comentario> comentarios;

    @ElementCollection
    @JoinColumn(name = "imagen", nullable = false)
    private List<String> imagenes;

    /**
     * Constructor completo para crear un lugar
     *
     * @param nombre,        nombre que tendrá el lugar
     * @param descripcion,   una descripción del lugar
     * @param tipoLugar,     el tipo de sitio que es el lugar
     * @param ciudadLugar,   en la cuál esta ubicado el lugar
     * @param fechaCreacion, fecha de registro del lugar
     * @param latitud,       latitud de la ubicación del lugar
     * @param longitud,      longitud de la ubicación del lugar
     * @param estado,        estado en el que se encuentra el lugar
     * @param usuarioCreador
     */
    public Lugar(String nombre, String descripcion, TipoLugar tipoLugar, Ciudad ciudadLugar,
                 Date fechaCreacion, Float latitud, Float longitud,
                 EstadoAprobacion estado, Usuario usuarioCreador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoLugar = tipoLugar;
        this.ciudadLugar = ciudadLugar;
        this.fechaCreacion = fechaCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.usuarioCreador = usuarioCreador;
        this.imagenes = new ArrayList<>();
    }

    /**
     * Método que muestra los datos del lugar
     *
     * @return una cadena con los datos del lugar
     */
    @Override
    public String toString() {
        return "Lugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipoLugar.getNombre() +
                ", ciudadLugar=" + ciudadLugar.getNombre() +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaAprobacion=" + fechaAprobacion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", estado=" + estado +
                ", usuarioCreador=" + usuarioCreador.getNombre() +
                '}';
    }

    public String getImagenPrincipal() {

        if (imagenes != null && !imagenes.isEmpty()) {
            return imagenes.get(0);
        } else {
            return "default.png";
        }
    }

    public int calificacionPromedio() {

        int res = 0;
        for (int i = 0; i < comentarios.size(); i++) {
            res += comentarios.get(i).getCalificacion();
        }
        return comentarios.size() == 0 ? res / comentarios.size() : 0;
    }
}
