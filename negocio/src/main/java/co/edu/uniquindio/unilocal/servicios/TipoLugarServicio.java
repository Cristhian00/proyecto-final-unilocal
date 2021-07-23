package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.TipoLugar;

import java.util.List;

public interface TipoLugarServicio {

    TipoLugar registrarTipoLugar(TipoLugar t) throws Exception;

    TipoLugar modificarTipoLugar(TipoLugar t) throws Exception;

    boolean eliminarTipoLugar(String nombre) throws Exception;

    TipoLugar obtenerTipoLugar(int id) throws Exception;

    TipoLugar obtenerTipoLugar(String nombre) throws Exception;

    List<TipoLugar> listarTipoLugar();
}
