package co.edu.uniquindio.unilocal.converter;

import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class UsuarioConverter implements Converter<Usuario>, Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public Usuario getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s != null && !"".equals(s)) {
                return usuarioServicio.obtenerUsuario(s);
            }
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(uiComponent.getId() + ": cédula no valida"));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Usuario usuario) {

        if(usuario != null){
            return usuario.getCedula();
        }
        return "";
    }
}
