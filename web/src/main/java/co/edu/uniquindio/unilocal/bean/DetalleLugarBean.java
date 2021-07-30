package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.dto.MarkerDTO;
import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.entidades.Horario;
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
import java.util.ArrayList;
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

    @Getter
    @Setter
    private List<String> imagenes;

    @Getter
    @Setter
    private List<String> imagenesBase;

    @PostConstruct
    public void init() {

        if (idLugar != null && !"".equals(idLugar)) {
            try {
                int id = Integer.parseInt(idLugar);
                this.lugar = lugarServicio.obtenerLugar(id);
                this.comentarios = lugarServicio.obtenerComentarios(id);
                this.horarios = lugarServicio.obtenerHorarios(id);
                this.imagenes = lugar.getImagenes();
                this.imagenesBase = obetenerNameBaseImg(this.imagenes);
                System.out.println("IMG base = " + this.imagenesBase);

                PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(
                        new MarkerDTO(lugar.getId(), lugar.getNombre(), lugar.getTipoLugar().getNombre(),
                                lugar.getDescripcion(), lugar.getLatitud(), lugar.getLongitud())) + ");");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> obetenerNameBaseImg(List<String> imagenes) {

        int pos = 0;
        List<String> nameBase = new ArrayList<>();

        for (int i = 0; i < imagenes.size(); i++) {
            for (int j = 0; j < imagenes.get(i).length(); j++) {
                if (imagenes.get(i).charAt(j) == '.') {
                    pos = j;
                    break;
                }
            }
            nameBase.add(imagenes.get(i).substring(0, pos));
        }
        return nameBase;
    }
}
