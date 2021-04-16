package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Comentario;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {
}
