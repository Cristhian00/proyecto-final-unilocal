package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Administrador;
import co.edu.uniquindio.unilocal.entidades.Moderador;
import co.edu.uniquindio.unilocal.entidades.Persona;
import co.edu.uniquindio.unilocal.servicios.AdministradorServicio;
import co.edu.uniquindio.unilocal.servicios.ModeradorServicio;
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
public class AdministradorBean implements Serializable {

    private final AdministradorServicio adminServicio;
    private final ModeradorServicio moderadorServicio;

    @Getter
    @Setter
    private Moderador moderador;

    @Getter
    @Setter
    private Administrador administrador;

    @Getter
    @Setter
    private List<Moderador> moderadores;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Autowired
    public AdministradorBean(AdministradorServicio adminServicio, ModeradorServicio moderadorServicio) {
        this.adminServicio = adminServicio;
        this.moderadorServicio = moderadorServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.moderador = new Moderador();
        this.moderadores = moderadorServicio.listaModerador();
    }

    public String registrarModerador() {

        FacesMessage msg;
        try {
            if (personaLogin != null) {
                System.out.println("Entre al registrpo");
                moderador.setAdministrador((Administrador) personaLogin);
                moderadorServicio.registraModerador(moderador);

                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Alerta", "El registro fue exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
                return "/administrador/administrador.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
        }
        return "";
    }

    public String eliminarModerador(String cedula) {

        FacesMessage msg;
        try {
            moderadorServicio.eliminarModerador(cedula);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se elimino con exito");
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
            return "#";
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
        }
        return "";
    }

}
