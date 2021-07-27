package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Persona;
import co.edu.uniquindio.unilocal.repositorios.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PersonaServicioImp implements PersonaServicio{

    @Autowired
    private PersonaRepo personaRepo;

    @Override
    public Persona login(String emailOrNick, String contrasenia) throws Exception {

        Optional<Persona> persona = personaRepo.findByEmailAndContrasenia(emailOrNick, contrasenia);

        if(persona.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return persona.get();
    }
}
