package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class UsuarioRegBean implements Serializable {

    private final UsuarioServicio usuServicio;
    private final CiudadServicio ciudadServicio;

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    public UsuarioRegBean(UsuarioServicio usuServicio, CiudadServicio ciudadServicio){
        this.usuServicio = usuServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.usuario = new Usuario();
        this.ciudades = ciudadServicio.listarCiudadesOrdenadas();
    }

    public String registrarUsuario() {

        FacesMessage msg;
        try {
            usuServicio.registraUsuario(usuario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje-usuario", msg);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-usuario", msg);
        }
        return "";
    }
}
