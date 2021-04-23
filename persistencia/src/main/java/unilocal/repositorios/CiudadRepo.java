package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Ciudad;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {
}