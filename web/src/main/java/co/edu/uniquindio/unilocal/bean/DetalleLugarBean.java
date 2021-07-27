package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class DetalleLugarBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Value("#{param['lugar']}")
    private String idLugar;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Comentario> comentarios;

    @Getter
    @Setter
    private List<Horario> horarios;

    @PostConstruct
    public void init() {

        if (idLugar != null && !"".equals(idLugar)) {
            try {
                int id = Integer.parseInt(idLugar);
                this.lugar = lugarServicio.obtenerLugar(id);
                this.comentarios = lugarServicio.obtenerComentarios(id);
                this.horarios = lugarServicio.obtenerHorarios(id);

                PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(
                        new MarkerDTO(lugar.getId(), lugar.getNombre(), lugar.getTipoLugar().getNombre(),
                                lugar.getDescripcion(), lugar.getLatitud(), lugar.getLongitud())) + ");");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
