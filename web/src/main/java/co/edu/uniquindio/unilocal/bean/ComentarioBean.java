package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.servicios.ComentarioServicio;
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
public class ComentarioBean implements Serializable {

    private final ComentarioServicio comentarioServicio;

    @Getter @Setter
    private Comentario comentario;

    public ComentarioBean(ComentarioServicio comentarioServicio) {
        this.comentarioServicio = comentarioServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.comentario = new Comentario();
    }

    public void registrarComentario(){

        FacesMessage msg;
        try {
            comentarioServicio.registrarComentario(comentario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void modificarComentario(){

        FacesMessage msg;
        try {
            comentarioServicio.modificarComentario(comentario);
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
