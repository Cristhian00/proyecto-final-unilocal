package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Imagen;

import java.util.List;

public interface ImagenServicio {

    Imagen registrarImagen(Imagen m) throws Exception;

    Imagen modificarImagen(Imagen m) throws Exception;

    boolean eliminarImagen(int id) throws Exception;

    Imagen obtenerImagen(int id) throws Exception;

    List<Imagen> listarImagenes();
}
