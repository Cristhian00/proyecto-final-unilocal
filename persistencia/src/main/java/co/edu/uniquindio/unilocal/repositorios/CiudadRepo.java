package co.edu.uniquindio.unilocal.repositorios;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional<Ciudad> findByNombre(String ciudad);

    Optional<Ciudad> findById(int id);

    @Query("select c from Ciudad c order by c.nombre")
    List<Ciudad> listarCiudadesOrdenadas();

    @Query("select u from Ciudad c, IN(c.usuarios) u where c.nombre = ?1")
    List<Usuario> obtenerUsuarios(String ciudad);

    @Query("select c.nombre, u from Ciudad c left join c.usuarios u")
    List<Object[]> obtenerUsuariosLeftJoin();

    @Query("select c from Ciudad c where c.nombre = ?1 and c.departamento.nombre = ?2")
    Optional<Ciudad> obtenerCiudadNombreDepartamento(String nombre, String departamento);
}
