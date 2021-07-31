package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@RequestScope
public class ModeradorBean implements Serializable {

    private final ModeradorServicio moderadorServicio;
    private final LugarServicio lugarServicio;

    @Getter
    @Setter
    private Moderador moderador;

    @Getter
    @Setter
    private Lugar lugar;

    @Setter
    @Getter
    private List<Lugar> lugaresPendientes;

    @Setter
    @Getter
    private List<Lugar> lugaresRevisados;

    @Setter
    @Getter
    private List<Lugar> lugaresAprobados;

    @Setter
    @Getter
    private List<Lugar> lugaresRechazados;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public ModeradorBean(ModeradorServicio moderadorServicio, LugarServicio lugarServicio) {
        this.moderadorServicio = moderadorServicio;
        this.lugarServicio = lugarServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.moderador = (Moderador) personaLogin;
        this.lugaresRevisados = moderadorServicio.obtenerLugaresRevisados(moderador.getCedula());
        this.lugaresAprobados = moderadorServicio.obtenerLugaresAprobados(moderador.getCedula());
        this.lugaresRechazados = moderadorServicio.obtenerLugaresRechazados(moderador.getCedula());
        this.lugaresPendientes = lugarServicio.obtenerLugaresPendientes();
    }

    public void modificarModerador() {

        FacesMessage msg;
        try {
            moderadorServicio.actualizarModerador(moderador);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Alerta", "Se actualizo con exito");
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
        }
    }

    public void aprobarLugar(int id) {

        try {
            lugar = lugarServicio.obtenerLugar(id);
            lugar.setEstado(EstadoAprobacion.APROBADO);
            lugar.setModerador(moderador);
            lugar.setFechaAprobacion(new Date());
            lugarServicio.modificarLugar(lugar);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
        }
    }

    public void rechazarLugar(int id) {
        try {
            lugar = lugarServicio.obtenerLugar(id);
            lugar.setEstado(EstadoAprobacion.RECHAZADO);
            lugar.setModerador(moderador);
            lugar.setFechaAprobacion(new Date());
            lugarServicio.modificarLugar(lugar);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-Moderador", msg);
        }
    }
}
