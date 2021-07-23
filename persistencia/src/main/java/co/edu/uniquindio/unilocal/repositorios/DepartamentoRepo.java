package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Departamento;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Integer> {

    Optional<Departamento> findById(Integer integer);

    Optional<Departamento> findByNombre(String departamento);

    @Query("select u from Departamento d, IN(d.ciudades) u where d.nombre = ?1")
    List<Ciudad> obtenerCiudades(String departamento);

    @Query("select d from Departamento d where d.nombre = ?1 and d.pais = ?2")
    Optional<Departamento> obtenerDepartamentoNombrePais(String nombre, String pais);
}
