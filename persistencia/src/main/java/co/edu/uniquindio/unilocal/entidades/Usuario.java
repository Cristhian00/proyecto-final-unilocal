package co.edu.uniquindio.unilocal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que contiene los atributos del usuario, el cual es el que registra o visita los lugares
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
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
    @JsonIgnore
    private List<Lugar> lugares;

    //Lugares que ha seleccionado como favoritos el usuario
    @ManyToMany
    @JoinTable(name = "favorito", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_lugar"))
    @JsonIgnore
    private List<Lugar> lugaresFavoritos;

    //Comentarios que ha realizado el usuario
    @OneToMany(mappedBy = "usuarioComentario")
    @JsonIgnore
    private List<Comentario> comentarios;

    /**
     * Constructor completo para crear un usuario
     *
     * @param cedula,       número de cédula del usuario
     * @param nombre,       nombre del usuario
     * @param email,        correo electronico del usuario
     * @param contrasenia,  contraseña con la que el usuario iniciara sesión
     * @param nickname,     nombre de usuario con el que iniciara sesión el usuario
     * @param ciudadUsuario
     */
    public Usuario(String cedula, String nombre, String email, String contrasenia,
                   String nickname, Ciudad ciudadUsuario) {
        super(cedula, nombre, email, contrasenia, nickname);
        this.ciudadUsuario = ciudadUsuario;
        this.telefono = new HashMap<>();
        this.lugaresFavoritos = new ArrayList<>();
    }

    public void addLugarFavorito(Lugar l){
        if(this.lugaresFavoritos == null){
            this.lugaresFavoritos = new ArrayList<>();
        }
        if(l != null){
            this.lugaresFavoritos.add(l);
        }
    }

    public void removeLugarFavorito(Lugar l){
        if(this.lugaresFavoritos == null){
            this.lugaresFavoritos = new ArrayList<>();
        } else if(l != null){
            for(int i = 0; i < this.lugaresFavoritos.size(); i++){
                if(this.lugaresFavoritos.get(i).getId() == l.getId()){
                    this.lugaresFavoritos.remove(i);
                }
            }
        }
    }

    /**
     * Método que muestra los datos que tiene el usuario
     *
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
                ", ciudadUsuario=" + this.getCiudadUsuario().getNombre() + '\'' +
                '}';
    }
}
