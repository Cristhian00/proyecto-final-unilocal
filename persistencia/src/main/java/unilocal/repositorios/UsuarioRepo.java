package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {
}
