package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Comentario;

import java.util.Date;
import java.util.List;

public interface ComentarioServicio {

    Comentario registrarComentario(Comentario c) throws Exception;

    Comentario modificarComentario(Comentario c) throws Exception;

    boolean eliminarComentario(Comentario c) throws Exception;

    Comentario obtenerComentario(String cedula, int id, Date fecha) throws Exception;

    List<Comentario> listarDepartamento();
}
