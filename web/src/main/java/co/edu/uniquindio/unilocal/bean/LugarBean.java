package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import co.edu.uniquindio.unilocal.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoLugarServicio tipoLugarServicio;
    private final HorarioServicio horarioServicio;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<TipoLugar> tipos;

    @Getter
    @Setter
    private List<Horario> horarios;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio,
                     TipoLugarServicio tipoLugarServicio, HorarioServicio horarioServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoLugarServicio = tipoLugarServicio;
        this.horarioServicio = horarioServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.lugar = new Lugar();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipos = tipoLugarServicio.listarTipoLugar();
        this.horarios = horarioServicio.listarHorario();
    }

    public String registrarLugar() {

        FacesMessage msg;

        try {
            if (lugar.getLatitud() != 0 && lugar.getLongitud() != 0) {

                lugar.setUsuarioCreador(usuarioServicio.obtenerUsuario("1030678"));

                lugarServicio.registrarLugar(lugar);

                return "lugarCreado?faces-redirect=true";
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Alerta", "Es necesario ubicar el lugar en el mapa");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
        }
        return null;
    }
}
