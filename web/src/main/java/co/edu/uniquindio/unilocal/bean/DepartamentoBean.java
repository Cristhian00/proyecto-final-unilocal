package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.servicios.DepartamentoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@RequestScope
public class DepartamentoBean implements Serializable {

    private final DepartamentoServicio depaServicio;

    @Getter @Setter
    private Departamento departamento;

    public DepartamentoBean(DepartamentoServicio depaServicio) {
        this.depaServicio = depaServicio;
    }

    @PostConstruct
    public void inicializar(){
        departamento = new Departamento();
    }

    public void registrarDepartamento(){

        FacesMessage msg;
        try {
            depaServicio.registrarDepartamento(departamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void modificarDepartamento(){

        FacesMessage msg;
        try {
            depaServicio.modificarDepartamento(departamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se modifico con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
