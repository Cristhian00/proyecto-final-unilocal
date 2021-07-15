package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Usuario;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    List<Ciudad> findAll();

    Ciudad findByNombre(String ciudad);

    @Query("select u from Ciudad c, IN(c.usuarios) u where c.nombre = ?1")
    List<Usuario> obtenerUsuarios(String ciudad);

    @Query("select c.nombre, u from Ciudad c left join c.usuarios u")
    List<Object[]> obtenerUsuariosLeftJoin();
}
