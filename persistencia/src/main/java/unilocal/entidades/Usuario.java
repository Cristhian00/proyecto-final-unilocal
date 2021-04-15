package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    @JoinColumn(name = "telefono")
    private Map<String, String> telefono;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "ciudad", nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuarioLugar")
    private List<Lugar> lugares;

    @ManyToMany
    private List<Lugar> lugaresFavoritos;

    @OneToMany(mappedBy = "usuarioComentario")
    private List<Comentario> comentarios;

    public Usuario (){
        super();
    }

    public Usuario(String cedula, String nombre, String email, String contrasenia, String nickname, Map<String, String> telefono) {
        super(cedula, nombre, email, contrasenia, nickname);
        this.telefono = telefono;
    }

    public Map<String, String> getTelefono() {
        return telefono;
    }

    public void setTelefono(Map<String, String> telefono) {
        this.telefono = telefono;
    }
}
