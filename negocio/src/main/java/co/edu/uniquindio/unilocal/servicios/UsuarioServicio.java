package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registraUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    Usuario obtenerUsuario(String cedula) throws Exception;

    boolean eliminarUsuario(String cedula) throws Exception;

    List<Usuario> listaUsuarios();

    List<Lugar> obtenerLugares(String cedula);

    List<Lugar> obtenerLugaresFavoritos(String cedula);
}
