package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoLugarRepo extends JpaRepository<TipoLugar, Integer> {

    Optional<TipoLugar> findById(int id);

    Optional<TipoLugar> findByNombre(String nombre);
}
