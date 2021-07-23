package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@RequestScope
public class CiudadBean implements Serializable {

    private final CiudadServicio ciudadServicio;
    private Ciudad ciudad;

    public CiudadBean(CiudadServicio ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void inicializar(){
        ciudad = new Ciudad();
    }

    public void registrarCiudad(){

        FacesMessage msg;
        try {
            ciudadServicio.registrarCiudad(ciudad);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void modificarCiudad(){

        FacesMessage msg;
        try {
            ciudadServicio.modificarCiudad(ciudad);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se modifico con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
