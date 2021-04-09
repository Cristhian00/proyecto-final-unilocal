package unilocal.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Administrador implements Serializable {

    @Id
    private String cedula;
}
