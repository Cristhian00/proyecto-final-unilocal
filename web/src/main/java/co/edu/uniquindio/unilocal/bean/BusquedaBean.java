package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import com.google.gson.Gson;
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

    private String busqueda;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

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
                            l.getDescripcion(), l.getLatitud(), l.getLongitud())).collect(Collectors.toList())) + ");");
        }

    }

    public String buscar() {
        if (!busqueda.isEmpty()) {
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }
        return "";
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getBusquedaParam() {
        return busquedaParam;
    }

    public void setBusquedaParam(String busquedaParam) {
        this.busquedaParam = busquedaParam;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }
}
