package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleLugarBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Value("#{param['lugar']}")
    private String idLugar;

    @Value(value = "#{seguridadBean.persona}")
    private Persona persona;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Comentario> comentarios;

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private List<String> imagenes;

    @Getter
    @Setter
    private String favorito;

    @PostConstruct
    public void init() {

        if (idLugar != null && !"".equals(idLugar)) {
            try {
                int id = Integer.parseInt(idLugar);
                this.lugar = lugarServicio.obtenerLugar(id);
                this.comentarios = lugarServicio.obtenerComentarios(id);
                this.horarios = lugarServicio.obtenerHorarios(id);
                this.imagenes = lugar.getImagenes();

                PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(
                        new MarkerDTO(lugar.getId(), lugar.getNombre(), lugar.getTipoLugar().getNombre(),
                                lugar.getDescripcion(), lugar.getLatitud(), lugar.getLongitud())) + ");");

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
            }
        }
    }

    public boolean esFavorito(){
        return lugarServicio.obtenerUsuarioFavorito(lugar.getId(), persona.getCedula());
    }

    public String cambiarFavorito() {

        if(esFavorito()){
            System.out.println("true favorito");
            lugarServicio.agregarUsuarioFavorito(lugar.getId(), persona.getCedula());
        } else {
            System.out.println("false favorito");
            lugarServicio.eliminarUsuarioFavorito(lugar.getId(), persona.getCedula());
        }
        return "/detalleLugar.xhtml?faces-redirect=true&amp;lugar=" + lugar.getId();
    }

}
