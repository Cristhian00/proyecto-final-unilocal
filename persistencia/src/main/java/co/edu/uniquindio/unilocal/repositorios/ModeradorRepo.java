package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, String> {

    //Obtiene los moderadores que tienen un nombre especifico
    List<Moderador> findByNombre(String nombre);

    //Obtiene el moderador que tiene un email especifico
    Optional<Moderador> findByEmail(String email);

    //Obtiene el moderador que tiene un nickname especifico
    Optional<Moderador> findByNickname(String nickname);

    //Obtiene el moderador que tiene un email especifico
    Optional<Moderador> findByCedula(String cedula);

    @Query("select m from Moderador m where m.cedula = ?1")
    Moderador obtenerModeradorCedula(String cedula);

    //Obtiene la lista de moderadores de la base de datos
    @Query("select m from Moderador m")
    List<Moderador> obtenerModeradores();

    @Query("select l from Moderador m, IN(m.lugares) l where m.cedula = ?1")
    List<Lugar> obtenerLugaresRevisados(String cedula);

    @Query("select l from Moderador m, IN(m.lugares) l where m.cedula = ?1 and l.estado = 'APROBADO'")
    List<Lugar> obtenerLugaresAprobados(String cedula);

    @Query("select l from Moderador m, IN(m.lugares) l where m.cedula = ?1 and l.estado = 'RECHAZADO'")
    List<Lugar> obtenerLugaresRechazados(String cedula);
}
