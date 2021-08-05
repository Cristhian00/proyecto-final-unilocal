package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepo extends JpaRepository<Persona, String> {

    Optional<Persona> findByNicknameAndContrasenia(String nickname, String contrasenia);

    Optional<Persona> findByEmailAndContrasenia(String email, String contrasenia);

    Optional<Persona> findByCedulaAndEmail(String cedula, String email);
}
