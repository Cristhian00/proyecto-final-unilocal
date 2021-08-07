package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
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

    @Getter
    @Setter
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
                    FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);

                    return "/usuario/usuario.xhtml?faces-redirect=true";
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Alerta", "Es necesario ubicar el lugar en el mapa y subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
                }
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
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
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_lugar", msg);
        }
        return null;
    }

    public void onRowEdit(RowEditEvent<Horario> event) {
        FacesMessage msg = new FacesMessage("Horario Edited", String.valueOf(event.getObject().getCodigo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Horario> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getCodigo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public ArrayList<Horario> crearHorarios(){

        ArrayList<Horario> horarios = new ArrayList<>();

        Horario h1 = new Horario("LUNES","00:00","00:00",null);
        Horario h2 = new Horario("MARTES","00:00","00:00",null);
        Horario h3 = new Horario("MIERCOLES","00:00","00:00",null);
        Horario h4 = new Horario("JUEVES","00:00","00:00",null);
        Horario h5 = new Horario("VIERNES","00:00","00:00",null);
        Horario h6 = new Horario("SABADO","00:00","00:00",null);
        Horario h7 = new Horario("DOMINGO","00:00","00:00",null);

        horarios.add(h1);
        horarios.add(h2);
        horarios.add(h3);
        horarios.add(h4);
        horarios.add(h5);
        horarios.add(h6);
        horarios.add(h7);

        return horarios;
    }

    public String[] diasHorario(){

        String[] dias = new String[7];
        dias[0] = "Lunes";
        dias[1] = "Martes";
        dias[2] = "Miercoles";
        dias[3] = "Jueves";
        dias[4] = "Viernes";
        dias[5] = "Sabado";
        dias[6] = "Domingo";

        return dias;
    }

    public String[] horaAperCierre(){

        String[] horas = new String[24];
        horas[0] = "00:00";
        horas[1] = "01:00";
        horas[2] = "02:00";
        horas[3] = "03:00";
        horas[4] = "04:00";
        horas[5] = "05:00";
        horas[6] = "06:00";
        horas[7] = "07:00";
        horas[8] = "08:00";
        horas[9] = "09:00";
        horas[10] = "10:00";
        horas[11] = "11:00";
        horas[12] = "12:00";
        horas[13] = "13:00";
        horas[14] = "14:00";
        horas[15] = "15:00";
        horas[16] = "16:00";
        horas[17] = "17:00";
        horas[18] = "18:00";
        horas[19] = "19:00";
        horas[20] = "20:00";
        horas[21] = "21:00";
        horas[22] = "22:00";
        horas[23] = "23:00";

        return horas;
    }

}
