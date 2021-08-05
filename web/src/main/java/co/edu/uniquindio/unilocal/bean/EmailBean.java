package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Persona;
import co.edu.uniquindio.unilocal.servicios.EmailServico;
import co.edu.uniquindio.unilocal.servicios.PersonaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@RequestScope
public class EmailBean implements Serializable {

    @Autowired
    private EmailServico emailServico;

    @Autowired
    private PersonaServicio personaServicio;

    @Getter
    @Setter
    private Persona persona;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String cedula;

    private String asunto;

    private String mensaje;

    public String enviarCorreo() {

        if (cedula != null && email != null) {
            try{
                persona = personaServicio.recuperar(cedula, email);
                asunto = "Recuperar contraseña";

                mensaje = "Estos son sus datos de login en UNILOCAL. No comparta la información con ningún tercero\n\n"
                        + "Email: " + email + "\nContraseña: " + persona.getContrasenia() + "\n\nHasta luego.";

                emailServico.SendSimpleMessage(email, asunto, mensaje);
                return "/index.xhtml?faces-redirect=true";
            } catch (Exception e){
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje-recuperar", m);
            }
        }
        return null;
    }

}
