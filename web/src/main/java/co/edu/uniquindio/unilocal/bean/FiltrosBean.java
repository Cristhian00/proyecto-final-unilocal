package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.TipoLugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class FiltrosBean implements Serializable {

    @Getter
    @Setter
    private Ciudad ciudadParam;

    @Getter
    @Setter
    private TipoLugar categoriaParam;

    @Getter
    @Setter
    private String calificacionParam;

    @Getter
    @Setter
    private String rangoParam;

    @Getter
    @Setter
    private List<Lugar> lugares;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<TipoLugar> tipos;

    @Getter
    @Setter
    private List<String> rangos;

    @Getter
    @Setter
    private List<String> calificaciones;

    private final CiudadServicio ciudadServicio;
    private final TipoLugarServicio tipoLugarServicio;
    private final LugarServicio lugarServicio;

    public FiltrosBean(CiudadServicio ciudadServicio, TipoLugarServicio tipoLugarServicio, LugarServicio lugarServicio) {
        this.ciudadServicio = ciudadServicio;
        this.tipoLugarServicio = tipoLugarServicio;
        this.lugarServicio = lugarServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipos = tipoLugarServicio.listarTipoLugar();
        this.calificaciones = new ArrayList<String>();
        calificaciones.add("1 Estrella");
        calificaciones.add("2 Estrellas");
        calificaciones.add("3 Estrellas");
        calificaciones.add("4 Estrellas");
        calificaciones.add("5 Estrellas");
        this.rangos = new ArrayList<String>();
        rangos.add("2 kilometros");
        rangos.add("6 kilometros");
        rangos.add("10 kilometros");
    }

    public void buscarPorRango(){
        System.out.println("Entreeeeeeeee + " + rangoParam);
        if(rangoParam != null){
            System.out.println("Rango seleccionado = " + rangoParam);
        } if (ciudadParam != null){
            System.out.println("Rango seleccionado = " + ciudadParam.getNombre());
        }
    }
}
