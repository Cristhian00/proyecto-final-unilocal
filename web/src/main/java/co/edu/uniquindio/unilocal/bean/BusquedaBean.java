package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

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
            lugares = lugarServicio.buscarLugares(busquedaParam);
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
