package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.TipoLugarServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import java.io.Serializable;

@Component
@RequestScope
public class LugarBean implements Serializable {

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoLugarServicio tipoLugarServicio;
    private Lugar lugar;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio,
                     UsuarioServicio usuarioServicio, TipoLugarServicio tipoLugarServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoLugarServicio = tipoLugarServicio;
    }

    @PostConstruct
    public void inicializar(){
        lugar = new Lugar();
    }

    public String registrarLugar(){

        FacesMessage msg;

        try{
            lugar.setCiudadLugar(ciudadServicio.obtenerCiudad("Armenia"));
            lugar.setUsuarioCreador(usuarioServicio.obtenerUsuario("111"));
            lugar.setTipoLugar(tipoLugarServicio.obtenerTipoLugar(1));

            lugarServicio.registrarLugar(lugar);

            return "lugarCreado?faces-redirect=true";
        }catch (Exception e){
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
        }
        return null;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
