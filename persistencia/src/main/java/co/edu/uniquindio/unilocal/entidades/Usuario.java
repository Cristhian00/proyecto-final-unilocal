package co.edu.uniquindio.unilocal.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
    @NotEmpty(message = "Debe ingresar al menos un número de contacto")
    @Pattern(regexp = "\\(\\d{3}\\)\\d{3}\\-\\d{4}", message = "Debe ingresar un número celular valido")
    @Pattern(regexp = "\\(\\d{1}\\)\\d{3}\\-\\d{4}", message = "Debe ingresar un número telefonico valido")
    private Map<String, String> telefono;

    //Ciudad en la cuál reside el usuario
    @ManyToOne
    @Size(max = 255, message = "El nombre de la ciudad debe tener máximo 255 caracteres")
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
