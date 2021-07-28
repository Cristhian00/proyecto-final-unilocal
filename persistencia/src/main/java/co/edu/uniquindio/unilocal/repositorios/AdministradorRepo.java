package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {

    //Obtiene los adminitradores que tienen un nombre especifico
    List<Administrador> findByNombre(String nombre);

    //Obtiene el Administrador que tiene un email especifico
    Optional<Administrador> findByEmail(String email);

    //Obtiene el Administrador que tiene un nickname especifico
    Optional<Administrador> findByNickname(String nickname);

    //Obtiene el Administrador que tiene un email especifico
    Optional<Administrador> findByCedula(String cedula);

    @Query("select a from Administrador a where a.cedula = ?1")
    Administrador obtenerAdministradorCedula(String cedula);

    //Obtiene la lista de Administrador de la base de datos
    @Query("select a from Administrador a")
    List<Administrador> obtenerAdministradores();
}
