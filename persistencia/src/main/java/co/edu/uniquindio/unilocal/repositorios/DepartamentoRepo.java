package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Departamento;

import java.util.List;

@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Integer> {

    List<Departamento> findAll();

    Departamento findByNombre(String departamento);
}
