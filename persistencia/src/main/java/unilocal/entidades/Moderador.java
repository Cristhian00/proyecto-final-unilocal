package unilocal.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Moderador extends Persona implements Serializable {

    @OneToMany(mappedBy = "moderador")
    private List<Lugar> lugares;

    @ManyToOne
    private Administrador administrador;

    public Moderador (){
        super();
    }

    public Moderador(String cedula, String nombre, String email, String contrasenia, String nickname) {
        super(cedula, nombre, email, contrasenia, nickname);
    }
}
