package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Imagen;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen, Integer> {
}
