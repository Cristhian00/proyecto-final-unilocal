package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Imagen;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen, Integer> {

    Optional<Imagen> findById(int id);
}
