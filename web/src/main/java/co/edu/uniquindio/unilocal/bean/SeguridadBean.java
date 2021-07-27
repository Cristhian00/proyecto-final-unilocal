package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Administrador;
import co.edu.uniquindio.unilocal.entidades.Persona;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.servicios.PersonaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private PersonaServicio personaServicio;

    @Getter
    @Setter
    private Persona persona;

    @Getter
    @Setter
    private boolean autenticado;

    @Getter
    @Setter
    private String emailOrNick;

    @Getter
    @Setter
    private String contrasenia;

    @Getter
    @Setter
    private String rol;

    public String iniciarSesion() {

        if (emailOrNick != null && contrasenia != null) {

            try {
                persona = personaServicio.login(emailOrNick, contrasenia);

                if(persona instanceof Usuario){
                    rol = "usuario";
                } else if(persona instanceof Administrador){
                    rol = "administrador";
                } else{
                    rol = "moderador";
                }

                autenticado = true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje-sesion", m);
            }

        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
}
