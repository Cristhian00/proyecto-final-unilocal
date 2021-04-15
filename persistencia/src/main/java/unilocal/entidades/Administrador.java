package unilocal.entidades;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Administrador extends Persona implements Serializable {

    @OneToMany(mappedBy = "administrador")
    private List<Moderador> moderadores;

    public Administrador(){
        super();
    }

    public Administrador(String cedula, String nombre, String email, String contrasenia, String nickname) {
        super(cedula, nombre, email, contrasenia, nickname);
    }
}
