package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Persona;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.ComentarioServicio;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class UsuarioBean implements Serializable {

    private final UsuarioServicio usuServicio;
    private final CiudadServicio ciudadServicio;
    private final LugarServicio lugarServicio;

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private Ciudad ciudad;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Lugar> lugaresFavoritos;

    @Getter
    @Setter
    private List<Lugar> lugaresPropios;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public UsuarioBean(UsuarioServicio usuServicio, CiudadServicio ciudadServicio, LugarServicio lugarServicio) {
        this.usuServicio = usuServicio;
        this.ciudadServicio = ciudadServicio;
        this.lugarServicio = lugarServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.usuario = (Usuario) personaLogin;
        this.ciudades = ciudadServicio.listarCiudadesOrdenadas();
        this.lugaresPropios = usuServicio.obtenerLugares(usuario.getCedula());
        this.lugaresFavoritos = usuServicio.obtenerLugaresFavoritos(usuario.getCedula());
    }

    public void modificarUsuario() {

        FacesMessage msg;
        try {
            usuServicio.actualizarUsuario(usuario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se actualizo con exito");
            FacesContext.getCurrentInstance().addMessage("mensaje-usuario", msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-usuario", msg);
        }
    }

    public String irAlDetalleCreador(Integer id){
        return "/usuario/detalleLugarCreador.xhtml?faces-redirect=true&amp;lugar=" + id;
    }
}
