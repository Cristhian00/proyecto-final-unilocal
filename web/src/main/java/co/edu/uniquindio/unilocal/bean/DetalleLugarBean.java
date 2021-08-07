package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.servicios.ComentarioServicio;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class DetalleLugarBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

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

    @Getter
    @Setter
    private String msjComentario;

    @Getter
    @Setter
    private Integer raitingComentario;

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
                                lugar.getDescripcion(), lugar.getLatitud(), lugar.getLongitud(),
                                lugar.getImagenPrincipal(), lugar.calificacionPromedio())) + ");");

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
            }
        }
    }

    public boolean esFavorito() {
        return lugarServicio.obtenerUsuarioFavorito(lugar.getId(), persona.getCedula());
    }

    public String cambiarFavorito() throws Exception {

        Usuario u = usuarioServicio.obtenerUsuario(persona.getCedula());
        Lugar l = lugarServicio.obtenerLugar(lugar.getId());

        if (!esFavorito()) {
            u.addLugarFavorito(l);
            usuarioServicio.actualizarUsuario(u);
        } else {
            u.removeLugarFavorito(l);
            usuarioServicio.actualizarUsuario(u);
        }
        return "/detalleLugar.xhtml?faces-redirect=true&amp;lugar=" + lugar.getId();
    }

    public String agregarComentario() {

        FacesMessage msg;

        if (msjComentario != null && raitingComentario != null) {
            try {
                Usuario u = usuarioServicio.obtenerUsuario(persona.getCedula());
                Lugar l = lugarServicio.obtenerLugar(lugar.getId());
                Comentario c = new Comentario(msjComentario, raitingComentario, new Date(), u, l);

                comentarioServicio.registrarComentario(c);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Alerta", "El comentario se ha realizado con exito");
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
                return "/detalleLugar.xhtml?faces-redirect=true&amp;lugar=" + lugar.getId();
            } catch (Exception e) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
            }
        }
        return "";
    }
    
    public String irARuta(){
        return "/rutaLugar.xhtml?faces-redirect=true&amp;lng=" + lugar.getLongitud() + "&lat=" + lugar.getLatitud();
    }

}
