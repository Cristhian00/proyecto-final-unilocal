package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Lugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoLugar tipo;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "ciudad", nullable = false)
    private Ciudad ciudad;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "fecha_aprobacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;

    @Column(name = "latitud", nullable = false)
    private float latitud;

    @Column(name = "longitud", nullable = false)
    private float longitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAprobacion estado;

    @ElementCollection
    @JoinColumn(name = "telefono")
    private Map<String, String> telefono;

    @ManyToMany
    private List<Horario> horarios;

    @ManyToOne
    private Moderador moderador;

    @ManyToOne
    private Usuario usuarioLugar;

    @ManyToMany(mappedBy = "lugaresFavoritos")
    private List<Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "lugarComentario")
    private List<Comentario> comentarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public EstadoAprobacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAprobacion estado) {
        this.estado = estado;
    }

    public Map<String, String> getTelefono() {
        return telefono;
    }

    public void setTelefono(Map<String, String> telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lugar lugar = (Lugar) o;

        return id == lugar.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
