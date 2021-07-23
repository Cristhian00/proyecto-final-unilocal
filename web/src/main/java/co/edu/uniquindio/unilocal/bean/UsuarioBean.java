package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@RequestScope
public class UsuarioBean implements Serializable {

    private final UsuarioServicio usuServicio;
    private Usuario usuario;

    public UsuarioBean(UsuarioServicio usuServicio) {
        this.usuServicio = usuServicio;
    }

    @PostConstruct
    public void inicializar(){
        usuario = new Usuario();
    }

    public void registrarUsuario(){

        FacesMessage msg;
        try {
            usuServicio.registraUsuario(usuario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void modificarUsuario(){

        FacesMessage msg;
        try {
            usuServicio.actualizarUsuario(usuario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se actualizo con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
