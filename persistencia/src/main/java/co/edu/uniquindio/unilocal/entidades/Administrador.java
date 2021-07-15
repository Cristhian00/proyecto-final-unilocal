package co.edu.uniquindio.unilocal.entidades;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase es la que contiene los atributos del administrador, el cuál es el
 * encargado de aprobar a los moderadores
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Administrador extends Persona implements Serializable {

    //Lista de moderadores que ha aprobado
    @OneToMany(mappedBy = "administrador")
    private List<Moderador> moderadores;

    /**
     * Constructor vacio del administrador
     */
    public Administrador() {
        super();
    }

    /**
     * Constructor completo para Crear administradores
     *
     * @param cedula,      cédula del administrador
     * @param nombre,      nombre del administrador
     * @param email,       correo electronico del administrador
     * @param contrasenia, contraseña que tendrá para iniciar sesión
     * @param nickname,    nombre de ususario que tendra el administrador para iniciar sesión
     */
    public Administrador(String cedula, String nombre, String email, String contrasenia, String nickname) {
        super(cedula, nombre, email, contrasenia, nickname);
    }

    /**
     * Método que obtiene la lista de moderadores que ha aprobado el administrador
     *
     * @return la lista de moderadores aprobados
     */
    public List<Moderador> getModeradores() {
        return moderadores;
    }

    /**
     * Método que modifica la lista de moderadores que ha aprobado el administrador
     *
     * @param moderadores, la nueva lista de moderadores
     */
    public void setModeradores(List<Moderador> moderadores) {
        this.moderadores = moderadores;
    }

    @Override
    public String toString() {

        return "Administrador{" +
                "cedula='" + this.getCedula() + '\'' +
                ", nombre='" + this.getNombre() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", contrasenia='" + this.getContrasenia() + '\'' +
                ", nickname='" + this.getNickname() + '\'' +
                '}';
    }
}
