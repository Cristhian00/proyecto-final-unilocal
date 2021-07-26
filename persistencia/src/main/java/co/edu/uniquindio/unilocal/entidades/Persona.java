package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "La cédula es obligatoria")
    @Size(min = 6, max = 12, message = "El número de la cédula solo puede tener entre 6 y 12 números")
    private String cedula;

    //Nombre de la persona
    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 100, message = "El nombre de la persona debe tener entre 5 y 100 caracteres")
    private String nombre;

    //Correo electronico de la persona
    @Column(name = "email", length = 60, nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un email con una estructura valida")
    @Size(max = 60, message = "El email debe tener máximo 60 caracteres")
    private String email;

    //Contraseña con la que iniciara sesión la persona
    @Column(name = "contrasenia", length = 100, nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 5 y 100 caracteres")
    private String contrasenia;

    //Nombre de usuario de la persona
    @Column(name = "nickname", length = 50, nullable = false, unique = true)
    @NotBlank(message = "El nickname es obligatorio")
    @Size(min = 3, max = 50, message = "El nickname debe tener entre 3 y 50 caracteres")
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
