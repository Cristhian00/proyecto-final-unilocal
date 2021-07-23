package co.edu.uniquindio.unilocal.bean;

import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import co.edu.uniquindio.unilocal.servicios.TipoLugarServicio;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@Component
@RequestScope
public class TipoLugarBean implements Serializable {

    private final TipoLugarServicio tipoLugarServicio;
    private TipoLugar tipoLugar;

    public TipoLugarBean(TipoLugarServicio tipoLugarServicio) {
        this.tipoLugarServicio = tipoLugarServicio;
    }

    public void inicializar(){
        tipoLugar = new TipoLugar();
    }


}
