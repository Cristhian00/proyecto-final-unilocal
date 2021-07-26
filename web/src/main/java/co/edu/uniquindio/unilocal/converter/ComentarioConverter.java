package co.edu.uniquindio.unilocal.converter;

import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.servicios.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class ComentarioConverter implements Converter<Comentario>, Serializable {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Override
    public Comentario getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s != null && !"".equals(s)) {
                int id = Integer.parseInt(s);
                return comentarioServicio.obtenerComentario(id);
            }
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(uiComponent.getId() + ": id no valida"));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Comentario comentario) {

        if(comentario != null){
            return "" + comentario.getId();
        }
        return "";
    }
}
