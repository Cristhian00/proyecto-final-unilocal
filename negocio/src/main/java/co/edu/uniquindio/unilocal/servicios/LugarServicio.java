package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Lugar;

import java.util.List;

public interface LugarServicio {

    Lugar registrarLugar(Lugar l) throws Exception;

    Lugar modificarLugar(Lugar l) throws Exception;

    boolean eliminarLugar(String nombre) throws Exception;

    Lugar obtenerLugar(String nombre) throws Exception;

    List<Lugar> listarLugar();

    List<Lugar> buscarLugares(String nombre);
}
