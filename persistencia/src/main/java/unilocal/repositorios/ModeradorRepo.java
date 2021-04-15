package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Moderador;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, String> {
}
