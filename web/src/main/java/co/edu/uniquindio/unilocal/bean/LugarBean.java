package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Value("${upload.url}")
    private String urlImagenes;

    private List<String> imagenes;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

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
        this.imagenes = new ArrayList<>();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipos = tipoLugarServicio.listarTipoLugar();
        this.horarios = horarioServicio.listarHorario();
    }

    public String registrarLugar() {

        FacesMessage msg;

        try {
            if (personaLogin != null) {

                if (lugar.getLatitud() != 0 && lugar.getLongitud() != 0 && !imagenes.isEmpty()) {

                    lugar.setUsuarioCreador((Usuario) personaLogin);
                    lugar.setImagenes(imagenes);
                    lugarServicio.registrarLugar(lugar);

                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Alerta", "El lugar se creó correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Alerta", "Es necesario ubicar el lugar en el mapa y subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
                }
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
        }
        return null;
    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen != null) {
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile file) {
        try {
            InputStream input = file.getInputStream();
            String filename = FilenameUtils.getName((file.getFileName()));
            String basename = FilenameUtils.getBaseName(filename) + "_";
            String extension = "." + FilenameUtils.getExtension((filename));
            File fileDest = File.createTempFile(basename, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);
            return fileDest.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
