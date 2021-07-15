package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Departamento;

import java.util.List;

public interface DepartamentoServicio {

    Departamento registrarDepartamento(Departamento d) throws Exception;

    Departamento modificarDepartamento(Departamento d) throws Exception;

    boolean eliminarDepartamento(Departamento d) throws Exception;

    Departamento obtenerDepartamento(String nombre) throws Exception;

    List<Departamento> listarDepartamento();
}
