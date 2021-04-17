package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Comentario;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {
}