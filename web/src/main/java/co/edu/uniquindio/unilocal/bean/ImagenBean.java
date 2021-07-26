package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Imagen;
import co.edu.uniquindio.unilocal.servicios.ImagenServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@RequestScope
public class ImagenBean implements Serializable {

    private final ImagenServicio imagenServicio;

    public ImagenBean(ImagenServicio imagenServicio) {
        this.imagenServicio = imagenServicio;
    }

    @Getter
    @Setter
    private Imagen imagen;

    public void inicializar(){
        this.imagen = new Imagen();
    }

    public void registrarImagen(){

        FacesMessage msg;
        try {
            imagenServicio.registrarImagen(imagen);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "El registro fue exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje-imagen", msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-imagen", msg);
        }
    }

    public void modificarUsuario(){

        FacesMessage msg;
        try {
            imagenServicio.modificarImagen(imagen);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se actualizo con exito");
            FacesContext.getCurrentInstance().addMessage("mensaje-imagen", msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-imagen", msg);
        }
    }
}
