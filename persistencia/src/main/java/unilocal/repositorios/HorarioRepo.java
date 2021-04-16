package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Horario;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {
}
