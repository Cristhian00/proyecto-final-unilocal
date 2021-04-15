package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Administrador;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {
}
