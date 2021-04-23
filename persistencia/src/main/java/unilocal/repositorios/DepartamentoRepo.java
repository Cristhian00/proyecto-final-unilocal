package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Departamento;

@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Integer> {
}
