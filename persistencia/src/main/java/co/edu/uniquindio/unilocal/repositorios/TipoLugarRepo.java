package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.TipoLugar;

import java.util.Optional;

@Repository
public interface TipoLugarRepo extends JpaRepository<TipoLugar, Integer> {

    @Query("select t from TipoLugar t where t.id = ?1")
    Optional<TipoLugar> obtenerTipoLugar(int id);
}
