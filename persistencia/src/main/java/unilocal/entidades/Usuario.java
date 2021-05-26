package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Clase que contiene los atributos del usuario, el cual es el que registra o visita los lugares
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Usuario extends Persona implements Serializable {

    //Números telefonicos que tiene el usuario
    @ElementCollection
    @JoinTable(name = "usuario_telefono")
    private Map<String, String> telefono;

    //Ciudad en la cuál reside el usuario
    @ManyToOne
    private Ciudad ciudadUsuario;

    //Lugares que ha registrado el usuario
    @OneToMany(mappedBy = "usuarioCreador")
    private List<Lugar> lugares;

    //Lugares que ha seleccionado como favoritos el usuario
    @ManyToMany
    @JoinTable(name = "favorito", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_lugar"))
    private List<Lugar> lugaresFavoritos;

    //Comentarios que ha realizado el usuario
    @OneToMany(mappedBy = "usuarioComentario")
    private List<Comentario> comentarios;

    /**
     * Constructor vacio del usuario
     */
    public Usuario (){
        super();
    }

    /**
     * Constructor completo para crear un usuario
     * @param cedula, número de cédula del usuario
     * @param nombre, nombre del usuario
     * @param email, correo electronico del usuario
     * @param contrasenia, contraseña con la que el usuario iniciara sesión
     * @param nickname, nombre de usuario con el que iniciara sesión el usuario
     * @param ciudadUsuario
     */
    public Usuario(String cedula, String nombre, String email, String contrasenia,
                   String nickname, Ciudad ciudadUsuario) {
        super(cedula, nombre, email, contrasenia, nickname);
        this.ciudadUsuario = ciudadUsuario;
    }

    /**
     * Método que obtiene los números telefonicos del usuario
     * @return lista con los números telefonicos
     */
    public Map<String, String> getTelefono() {
        return telefono;
    }

    /**
     * Método que modifica los números telefonicos del usuario
     * @param telefono, lista con los números telefonicos modificados
     */
    public void setTelefono(Map<String, String> telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que obtiene la ciudad de residencia del usuario
     * @return ciudad de residencia
     */
    public Ciudad getCiudad() {
        return ciudadUsuario;
    }

    /**
     * Método que modifica la ciudad de residencia del usuario
     * @param ciudad, ciudad nueva de residencia
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudadUsuario = ciudad;
    }

    /**
     * Método que obtiene la lista de lugares que ha registrado el usuario
     * @return lista lugares
     */
    public List<Lugar> getLugares() {
        return lugares;
    }

    /**
     * Método que modifica la lista de lugares que ha registrado el usuario
     * @param lugares, lista de lugares a modificar
     */
    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    /**
     * Método que obtiene la lista de los lugares favoritos del usuario
     * @return lista de lugares
     */
    public List<Lugar> getLugaresFavoritos() {
        return lugaresFavoritos;
    }

    /**
     * Método que modifica la lista de los lugares favoritos del usuario
     * @param lugaresFavoritos, lista de lugares a modificar
     */
    public void setLugaresFavoritos(List<Lugar> lugaresFavoritos) {
        this.lugaresFavoritos = lugaresFavoritos;
    }

    /**
     * Método que obtiene los comentarios que ha ralizado el usuario
     * @return lista de los comentarios
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Método que modifica los comentarios que ha realizado el usuario
     * @param comentarios, lista de comentarios a modificar
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

      /**
     * Método que muestra los datos que tiene el usuario
     * @return una cadena con los datos del usuario
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "cedula='" + this.getCedula() + '\'' +
                ", nombre='" + this.getNombre() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", contrasenia='" + this.getContrasenia() + '\'' +
                ", nickname='" + this.getNickname() + '\'' +
                ", ciudadUsuario=" + this.getCiudad().getNombre() + '\'' +
                '}';
    }
}
