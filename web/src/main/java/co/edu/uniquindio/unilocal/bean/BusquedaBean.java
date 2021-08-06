package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter
    @Setter
    private String busqueda;

    @Getter
    @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter
    @Setter
    private List<Lugar> lugares;

    private final LugarServicio lugarServicio;

    public BusquedaBean(LugarServicio lugarServicio) {
        this.lugarServicio = lugarServicio;
    }

    @PostConstruct
    public void inicializar() {
        if (busquedaParam != null && !busquedaParam.isEmpty()) {
            this.lugares = lugarServicio.buscarLugaresPorPalabra(busquedaParam);

            PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(this.lugares.stream()
                    .map(l -> new MarkerDTO(l.getId(), l.getNombre(), l.getTipoLugar().getNombre(),
                            l.getDescripcion(), l.getLatitud(), l.getLongitud(), l.getImagenPrincipal(),
                            l.calificacionPromedio())).collect(Collectors.toList())) + ");");
        }

    }

    public String buscar() {
        if (!busqueda.isEmpty()) {
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }
        return "";
    }
}
