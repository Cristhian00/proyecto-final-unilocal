package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Ciudad;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad c) throws Exception;

    Ciudad modificarCiudad(Ciudad c) throws Exception;

    boolean eliminarCiudad(Ciudad c) throws Exception;

    Ciudad obtenerCiudad(String nombre) throws Exception;

    List<Ciudad> listarCiudades();
}
