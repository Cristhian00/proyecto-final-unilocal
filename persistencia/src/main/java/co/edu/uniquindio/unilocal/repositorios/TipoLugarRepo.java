package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.TipoLugar;

@Repository
public interface TipoLugarRepo extends JpaRepository<TipoLugar, Integer> {
}
