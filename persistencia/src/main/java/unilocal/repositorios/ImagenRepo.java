package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Imagen;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen, Integer> {
}
