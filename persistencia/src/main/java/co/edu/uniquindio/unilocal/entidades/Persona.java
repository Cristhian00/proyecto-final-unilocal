package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public abstract class Persona implements Serializable {

    //Número de cédula de la persona
    @Id
    @Column(name = "cedula", length = 12, nullable = false)
    @EqualsAndHashCode.Include
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

}
