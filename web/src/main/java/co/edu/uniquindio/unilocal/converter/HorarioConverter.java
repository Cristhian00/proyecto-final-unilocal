package co.edu.uniquindio.unilocal.converter;

import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.servicios.HorarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class HorarioConverter implements Converter<Horario>, Serializable {

    @Autowired
    private HorarioServicio horarioServicio;

    @Override
    public Horario getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s != null && !"".equals(s)) {
                int id = Integer.parseInt(s);
                return horarioServicio.obtenerHorario(id);
            }
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(uiComponent.getId() + ": id no valida"));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Horario horario) {

        if (horario != null) {
            return "" + horario.getCodigo();
        }
        return "";
    }
}
