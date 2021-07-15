package co.edu.uniquindio.unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase que contiene los atributos de una persona, la cual será la super clase para los
 * moderadores, administradores y usuarios
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Persona implements Serializable {

    //Número de cédula de la persona
    @Id
    @Column(name = "cedula", length = 12, nullable = false)
    private String cedula;

    //Nombre de la persona
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    //Correo electronico de la persona
    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    //Contraseña con la que iniciara sesión la persona
    @Column(name = "contrasenia", length = 100, nullable = false)
    private String contrasenia;

    //Nombre de usuario de la persona
    @Column(name = "nickname", length = 50, nullable = false, unique = true)
    private String nickname;

    /**
     * Constructor vacio de la persona
     */
    public Persona() {
        super();
    }

    /**
     * Constructor completo con la que se creara una persona
     *
     * @param cedula,      número de cédula de la persona
     * @param nombre,      nombre de la persona
     * @param email,       correo electronico de la persona
     * @param contrasenia, contraseña con la que inicara sesión
     * @param nickname,    nombre de usuario con la que iniciara sesión
     */
    public Persona(String cedula, String nombre, String email, String contrasenia, String nickname) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nickname = nickname;
    }

    /**
     * Método que obtiene el número de cédula de una persona
     *
     * @return número de cédula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Método que modifica el número de cédula de una persona
     *
     * @param cedula, número de cédula nuevo
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Método que obtiene el nombre de una persona
     *
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el nombre de una persona
     *
     * @param nombre, nombre nuevo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el correo electronico de una persona
     *
     * @return el correo electronico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que modifica el correo electronico de la persona
     *
     * @param email, correo electronico a modificar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que obtiene la contraseña de la persona
     *
     * @return contraseña
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Método que modifica la contraseña de la persona
     *
     * @param contrasenia, contraseña nueva
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Método que obtiene el nombre de usuario de la persona
     *
     * @return nombre de usuario
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Métdoo que modifica el nombre de usuario de la persona
     *
     * @param nickname, nombre de usuario a modificar
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Método que compara dos personas
     *
     * @param o, persona a comparar
     * @return true si las personas son la misma, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return cedula != null ? cedula.equals(persona.cedula) : persona.cedula == null;
    }

    @Override
    public int hashCode() {
        return cedula != null ? cedula.hashCode() : 0;
    }

}
