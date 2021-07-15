package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Horario;

import java.util.List;

public interface HorarioServicio {

    Horario registrarHorario(Horario h) throws Exception;

    Horario modificarHorario(Horario h) throws Exception;

    boolean eliminarHorario(Horario h) throws Exception;

    Horario obtenerHorario(int id) throws Exception;

    List<Horario> listarHorario();
}
