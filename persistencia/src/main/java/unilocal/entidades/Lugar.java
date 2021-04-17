package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Clase que contiene todos los atributos de un lugar y sus métodos unitarios
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Lugar implements Serializable {

    //Número de identificación del lugar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Nombre que tiene el lugar
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    //Descripción del lugar
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    //Tipo al que pertenece el lugar
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoLugar tipo;

    //Ciudad en la cuál esta el lugar
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "ciudad", nullable = false)
    private Ciudad ciudad;

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
    private double latitud;

    //Longitud de la ubicación del lugar
    @Column(name = "longitud", nullable = false, unique = true)
    private double longitud;

    //Estado en el que se encuentra el lugar
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAprobacion estado;

    //Números telefonicos con los que cuenta el lugar
    @ElementCollection
    @JoinColumn(name = "telefono")
    private Map<String, String> telefono;

    //Lista de horarios que tiene el lugar
    @ManyToMany
    private List<Horario> horarios;

    //Moderador que evaluo al lugar
    @ManyToOne
    private Moderador moderador;

    //Usuario que registro el lugar
    @ManyToOne
    private Usuario usuarioLugar;

    //Usuarios que han seleccionado al lugar como favorito
    @ManyToMany(mappedBy = "lugaresFavoritos")
    private List<Usuario> usuariosFavoritos;

    //Comentarios que se le han realizado al lugar
    @OneToMany(mappedBy = "lugarComentario")
    private List<Comentario> comentarios;

    /**
     * Constructor vacio del lugar
     */
    public Lugar(){
        super();
    }

    /**
     * Constructor completo para crear un lugar
     * @param nombre, nombre que tendrá el lugar
     * @param descripcion, una descripción del lugar
     * @param tipo, el tipo de sitio que es el lugar
     * @param ciudad, en la cuál esta ubicado el lugar
     * @param fechaCreacion, fecha de registro del lugar
     * @param latitud, latitud de la ubicación del lugar
     * @param longitud, longitud de la ubicación del lugar
     * @param estado, estado en el que se encuentra el lugar
     */
    public Lugar(String nombre, String descripcion, TipoLugar tipo, Ciudad ciudad,
                 Date fechaCreacion, double latitud, double longitud, EstadoAprobacion estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.fechaCreacion = fechaCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    /**
     * Método que obtiene el número de identificación del lugar
     * @return el número de identificación
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el número de identificación de un lugar
     * @param id, número de identifiación a cambiar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que obtiene el nombre del lugar
     * @return el nombre del lugar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el nombre del lugar
     * @param nombre, nombre nuevo que tendrá el lugar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene la descripción del lugar
     * @return la descripción del lugar
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que modifica la descripción de un lugar
     * @param descripcion, nueva descripción que tendrá el lugar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método que obtiene el tipo de sitio que es el lugar
     * @return tipo de sitio
     */
    public TipoLugar getTipo() {
        return tipo;
    }

    /**
     * Método que modifica el tipo de sitio que es el lugar
     * @param tipo, tipo nuevo del lugar
     */
    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que obtiene la ciudad en la que esta ubicacdo el lugar
     * @return la ciudad de ubicación del lugar
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * Método que modifica la ciudad en la que esta ubicado el lugar
     * @param ciudad, nueva ciudad de ubicación del lugar
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Métodoq ue obtiene la fecha de creación del lugar
     * @return fecha en la que se creo el lugar
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Método que modifica la fecha de creación del lugar
     * @param fechaCreacion, fecha nueva de creación del luagr
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Método que obtiene la fecha de aprobación del lugar
     * @return la fecha en la que se aprobo el lugar
     */
    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    /**
     * Método que modifica la fecha de aprobación del lugar
     * @param fechaAprobacion, nueva fecha de aprobación
     */
    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    /**
     * Método que obtiene la latitud del lugar
     * @return un número con la latituda del lugar
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Métodoq ue modifica la latitud del lugar
     * @param latitud, nueva latitud que tendrá el lugar
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Método que obtiene la longitud del lugar
     * @return un número con la longitud del lugar
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Método que modifica la longitud del lugar
     * @param longitud, nueva longitud que tendrá el lugar
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Método que obtiene el estado en el que se encuentra el lugar
     * @return estado del lugar
     */
    public EstadoAprobacion getEstado() {
        return estado;
    }

    /**
     * Método que modifica el estado en el que se encuentra el lugar
     * @param estado, nuevo estado del lugar
     */
    public void setEstado(EstadoAprobacion estado) {
        this.estado = estado;
    }

    /**
     * Método que obtiene los números telefonicos que tiene un lugar
     * @return números telefonicos
     */
    public Map<String, String> getTelefono() {
        return telefono;
    }

    /**
     * Método que modifica los números telefonicos del lugar
     * @param telefono, números telefonicos a modificar
     */
    public void setTelefono(Map<String, String> telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que obtiene los horarios que tiene un lugar
     * @return listado de los horarios del lugar
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * Método que modifica los horarios que tiene un lugar
     * @param horarios, horarios nuevos a modificar
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * Método que obtiene el moderador del lugar
     * @return el moderador
     */
    public Moderador getModerador() {
        return moderador;
    }

    /**
     * Método que modifica el moderador del lugar
     * @param moderador, nuevo moderador a modificar
     */
    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    /**
     * Método que obtiene el usuario que registro el lugar
     * @return el usuario
     */
    public Usuario getUsuarioLugar() {
        return usuarioLugar;
    }

    /**
     * Método que modifica el ususario del lugar
     * @param usuarioLugar, nuevo usuario del lugar
     */
    public void setUsuarioLugar(Usuario usuarioLugar) {
        this.usuarioLugar = usuarioLugar;
    }

    /**
     * Método que obtiene los usuarios que han marcado al lugar como favorito
     * @return lista de usuarios
     */
    public List<Usuario> getUsuariosFavoritos() {
        return usuariosFavoritos;
    }

    /**
     * Método que modifica la lista de ususarios que marcaron al lugar como favorito
     * @param usuariosFavoritos, lista nueva de usuarios
     */
    public void setUsuariosFavoritos(List<Usuario> usuariosFavoritos) {
        this.usuariosFavoritos = usuariosFavoritos;
    }

    /**
     * Método que obtiene un comentario del lugar
     * @return comentario
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Método que modifica los comentarios hechos al lugar
     * @param comentarios, nuevos comentarios
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Método que compara un lugar con otro
     * @param o, lugar que se va a coparar
     * @return true si son el mismo lugar, de lo contrario false
     */
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

    /**
     * Método que muestra los datos del lugar
     * @return una cadena con los datos del lugar
     */
    @Override
    public String toString() {
        return "Lugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", ciudad=" + ciudad +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaAprobacion=" + fechaAprobacion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", estado=" + estado +
                '}';
    }
}
