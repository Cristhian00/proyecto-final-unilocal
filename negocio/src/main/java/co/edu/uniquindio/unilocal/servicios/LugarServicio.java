package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface LugarServicio {

    Lugar registrarLugar(Lugar l) throws Exception;

    Lugar modificarLugar(Lugar l) throws Exception;

    boolean eliminarLugar(String nombre) throws Exception;

    Lugar obtenerLugar(int id) throws Exception;

    Lugar obtenerLugar(String nombre) throws Exception;

    List<Lugar> listarLugar();

    List<Lugar> buscarLugaresPorPalabra(String nombre);

    List<Lugar> lugaresPorModerador(String moderador);

    List<Comentario> obtenerComentarios(Integer idLugar);

    List<Horario> obtenerHorarios(Integer idLugar);

    List<Lugar> obtenerLugaresPendientes();

    boolean obtenerUsuarioFavorito(int id, String cedula);

    void agregarUsuarioFavorito(int idLugar, String cedula);

    void eliminarUsuarioFavorito(int idLugar, String cedula);

}
