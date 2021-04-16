package unilocal.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Lugar;

@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {
}
