package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Moderador;

import java.util.List;

public interface ModeradorServicio {

    Moderador registraModerador(Moderador m) throws Exception;

    Moderador actualizarModerador(Moderador m) throws Exception;

    Moderador obtenerModerador(String cedula) throws Exception;

    boolean eliminarModerador(String cedula) throws Exception;

    List<Moderador> listaModerador();
}
