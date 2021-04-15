package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Persona implements Serializable {

    @Id
    @Column(name = "cedula", length = 12, nullable = false)
    private String cedula;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "contrasenia", length = 100, nullable = false)
    private String contrasenia;

    @Column(name = "nickname", length = 50, nullable = false, unique = true)
    private String nickname;

    public Persona(){
        super();
    }

    public Persona(String cedula, String nombre, String email, String contrasenia, String nickname) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nickname = nickname;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
