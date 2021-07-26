package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Comentario;

import java.util.Date;
import java.util.List;

public interface ComentarioServicio {

    Comentario registrarComentario(Comentario c) throws Exception;

    Comentario modificarComentario(Comentario c) throws Exception;

    boolean eliminarComentario(int id) throws Exception;

    Comentario obtenerComentario(int id) throws Exception;

    List<Comentario> listarComentarios();
}
