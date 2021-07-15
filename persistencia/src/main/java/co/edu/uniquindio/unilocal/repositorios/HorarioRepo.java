package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Horario;

import java.util.List;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {

    Horario findByCodigo(int codigo);

    List<Horario> findAll();
}
