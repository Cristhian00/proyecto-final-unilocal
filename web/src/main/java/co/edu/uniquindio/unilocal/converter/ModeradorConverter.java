package co.edu.uniquindio.unilocal.converter;

import co.edu.uniquindio.unilocal.entidades.Moderador;
import co.edu.uniquindio.unilocal.servicios.ModeradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class ModeradorConverter implements Converter<Moderador>, Serializable {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Override
    public Moderador getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s != null && !"".equals(s)) {
                return moderadorServicio.obtenerModerador(s);
            }
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(uiComponent.getId() + ": cédula no valida"));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Moderador moderador) {

        if(moderador != null){
            return moderador.getCedula();
        }
        return "";
    }
}
