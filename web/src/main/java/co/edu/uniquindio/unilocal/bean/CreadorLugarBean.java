package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.ComentarioServicio;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@Component
@RequestScope
public class CreadorLugarBean {

    private final UsuarioServicio usuServicio;
    private final CiudadServicio ciudadServicio;
    private final LugarServicio lugarServicio;
    private final ComentarioServicio comentarioServicio;

    public CreadorLugarBean(UsuarioServicio usuServicio, CiudadServicio ciudadServicio, LugarServicio lugarServicio, ComentarioServicio comentarioServicio) {
        this.usuServicio = usuServicio;
        this.ciudadServicio = ciudadServicio;
        this.lugarServicio = lugarServicio;
        this.comentarioServicio = comentarioServicio;
    }

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
    private String msjRespuesta;

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

            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Alerta", ex.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
            }
        }
    }

    public String responderComentario(int id){

        FacesMessage msg;
        System.out.println("entre responder comentario");
        if (msjRespuesta != null) {
            try {
                Comentario c = comentarioServicio.obtenerComentario(id);
                c.setRespuesta(msjRespuesta);
                comentarioServicio.modificarComentario(c);
                System.out.println("Qué paso aquí");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Alerta", "Se ha contestado el comentario");
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);

                return "/usuario/detalleLugarCreador.xhtml?faces-redirect=true&amp;lugar=" + lugar.getId();
            } catch (Exception e) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
            }
        }
        return "";
    }
}
