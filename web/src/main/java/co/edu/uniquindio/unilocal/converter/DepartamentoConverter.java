package co.edu.uniquindio.unilocal.converter;

import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.servicios.DepartamentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class DepartamentoConverter implements Converter<Departamento>, Serializable {

    @Autowired
    private DepartamentoServicio deparServicio;

    @Override
    public Departamento getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s != null && !"".equals(s)) {
                int id = Integer.parseInt(s);
                return deparServicio.obtenerDepartamento(id);
            }
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(uiComponent.getId() + ": id no valida"));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Departamento departamento) {

        if(departamento != null){
            return "" + departamento.getId();
        }
        return "";
    }
}
