package unilocal.repositorios;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Usuario;
import unilocal.entidades.Lugar;

import java.util.List;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    //Obtiene la lista de usuarios de la base de datos
    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    //Obtiene la lista de usuarios de la base de datos
    List<Usuario> findAllBy();

    //Obtiene la lista de usuarios de la base de datos pero paginables(si son muchos datos)
    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Pageable pagenable);

    //Obtiene la lista de usuarios de la base de datos ordenada
    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Sort sort);

    //Obtiene un usuario por su email y nombre (manera 1)
    @Query("select u from Usuario u where u.email = :email and u.nombre = :nombre")
    Usuario obtenerUsuarioPorEmailYNombre(@Param("email") String email, @Param("nombre") String nombre);

    //Obtiene un usuario por su email y nombre (manera 2)
    @Query("select u from Usuario u where u.email = ?1 and u.nombre = ?2")
    Usuario obtenerUsuarioPorEmailYNombre2(String email, String nombre);

    //Obtiene un usuario por su email y nombre con un método que lo infiere por su nombre (manera 3)
    Usuario findByEmailAndNombre(String email, String nombre);

    //Obtiene los usuarios que tienen un nombre especifico
    List<Usuario> findByNombre(String nombre);

    @Query("select f from Usuario u, IN(u.lugaresFavoritos) f where u.cedula = ?1")
    List<Lugar> obtenerLugaresFavoritos(String cedula);

    @Query("select u.email, l from Usuario u left join u.lugares l")
    List<Object[]> obtenerLugaresPublicadosEEmail();
}
