package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {

    Optional<Horario> findByCodigo(int codigo);

    @Query("select h from Horario h where h.dia = ?1 and h.horaApertura = ?2 and h.horaCierre = ?3")
    Optional<Horario> obtenerHorario(String dia, String inicio, String fin);

}
