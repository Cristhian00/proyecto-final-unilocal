package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class IncioBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Getter
    @Setter
    private List<Lugar> lugares;

    @PostConstruct
    public void inicializar(){
        this.lugares = lugarServicio.listarLugar();

        PrimeFaces.current().executeScript("crearMapa("+new Gson().toJson(this.lugares.stream()
                .map(l -> new MarkerDTO(l.getId(), l.getNombre(), l.getTipoLugar(),l.getDescripcion(), l.getLatitud(), l.getLongitud())).collect(Collectors.toList()))+");");
    }

    public String irADetalle(int id){
        return "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }
}
