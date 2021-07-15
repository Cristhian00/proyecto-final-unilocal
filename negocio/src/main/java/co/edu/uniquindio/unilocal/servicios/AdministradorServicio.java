package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Administrador;

import java.util.List;

public interface AdministradorServicio {

    Administrador registraAdministrador(Administrador a) throws Exception;

    Administrador actualizarAdministrador(Administrador a) throws Exception;

    Administrador obtenerAdministrador(String cedula) throws Exception;

    boolean eliminarAdministrador(String cedula) throws Exception;

    List<Administrador> listaAdministradores();
}
