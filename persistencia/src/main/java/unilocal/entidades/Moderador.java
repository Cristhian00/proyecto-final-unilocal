package unilocal.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos del moderador, el cúal es el encargado de
 * aprobar o rechazar los lugares que se registran en la plataforma
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Moderador extends Persona implements Serializable {

    //Lugares que ha evaluado el moderador
    @OneToMany(mappedBy = "moderador")
    private List<Lugar> lugares;

    //Administrador que aprobo al moderador
    @ManyToOne
    private Administrador administrador;

    /**
     * Constructor vacio del moderador
     */
    public Moderador (){
        super();
    }

    /**
     * Constructor completo que permite la cración de un moderador
     * @param cedula, número de cédula del moderador
     * @param nombre, nombre del moderador
     * @param email, correo electronico del moderador
     * @param contrasenia, contraseña con la que iniciara sesión
     * @param nickname, nombre de usuario con el que iniciara sesión
     */
    public Moderador(String cedula, String nombre, String email, String contrasenia, String nickname, Administrador administrador) {
        super(cedula, nombre, email, contrasenia, nickname);
        this.administrador = administrador;
    }

    /**
     * Método que obtiene la lista de lugares que ha evaluado el moderador
     * @return lista de lugares
     */
    public List<Lugar> getLugares() {
        return lugares;
    }

    /**
     * Método que modifica la lista de lugares que ha evaluado el moderador
     * @param lugares, nueva lista de lugares evaluados
     */
    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    /**
     * Método que obtiene al administrador que aprobo al moderador
     * @return el administrador
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Método que modifica el administrador que aprobo al moderador
     * @param administrador, administrador nuevo del moderador
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

     /**
     * Método que muestra los datos que tiene el moderador
     * @return una cadena con los datos del moderador
     */
    @Override
    public String toString() {
        return "Moderador{" +
                "cedula='" + this.getCedula() + '\'' +
                ", nombre='" + this.getNombre() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", contrasenia='" + this.getContrasenia() + '\'' +
                ", nickname='" + this.getNickname() + '\'' +
                ", administrador=" + administrador.getNombre() + '\'' +
                '}';
    }
}
