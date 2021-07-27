package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Persona;

public interface PersonaServicio {

    Persona login(String emailOrNick, String contrasenia) throws Exception;
}
