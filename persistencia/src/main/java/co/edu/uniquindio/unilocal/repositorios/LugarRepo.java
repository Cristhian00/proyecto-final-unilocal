package co.edu.uniquindio.unilocal.repositorios;


import co.edu.uniquindio.unilocal.dto.LugarComentariosDTO;
import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    Optional<Lugar> findByNombre(String nombre);

    @Query("select l from Lugar l where l.id = ?1")
    Lugar obtenerLugar(int id);

    @Query("select c from Comentario c where c.lugarComentario.id = ?1")
    List<Comentario> obtenerComentarios(Integer idLugar);

    @Query("select h from Horario h, IN(h.lugarHorario) l where l.id = ?1")
    List<Horario> obtenerHorarios(Integer idLugar);

    @Query("select l from Lugar l where l.nombre = ?1")
    Lugar obtenerLugarNombre(String nombre);

    //Lugares que esten aprobados por un moderador
    @Query("select l from Lugar l where l.nombre like concat('%', :nombre, '%') ")
    List<Lugar> buscarLugares(String nombre);

    Optional<Lugar> findByLatitudAndLongitud(Float latitud, Float longitud);

    List<Lugar> findAll();

    Optional<Lugar> findById(int id);

    @Query("select l.id, l.nombre, l.latitud, l.longitud from Lugar l where l.id = :idLugar")
    List<Object[]> obtenerInfoLugar(@Param("idLugar") int id);

    @Query("select l.id, l.nombre, l.latitud, l.longitud from Lugar l")
    List<Object[]> obtenerInfoLugares();

    @Query("select l.nombre, l.descripcion, l.ciudadLugar.nombre, l.tipoLugar.nombre " +
            "from Lugar l where l.moderador.cedula = ?1 and l.estado = 'APROBADO'")
    List<Object[]> obtenerLugaresAprobados(String cedulaModerador);

    @Query("select new co.edu.uniquindio.unilocal.dto.LugarComentariosDTO(l, c) " +
            "from Lugar l left join l.comentarios c")
    List<LugarComentariosDTO> obtenerComentariosLugares();

    @Query("select l from Lugar l join l.horarios h where h.dia = :dia and :horaActual " +
            "between h.horaApertura and h.horaCierre")
    List<Lugar> obtenerLugaresAbiertos(String dia, String horaActual);

    @Query("select l.tipoLugar.nombre, count(l) as total from Lugar l where l.estado = 'APROBADO' " +
            "group by l.tipoLugar order by total desc")
    List<String> obetenerTipoLugarPreferido();

    @Query("select u from Lugar l, IN(l.usuariosFavoritos) u where l.id = ?1")
    List<Usuario> obtenerUsuariosFavoritos(int id);

    @Query("select l from Lugar l, IN(l.moderador) m where m.cedula = ?1")
    List<Lugar> obtenerLugaresModerador(String cedula);

    @Query("select l from Lugar l where l.estado = 'PENDIENTE'")
    List<Lugar> obtenerLugaresPendientes();
    @Query("select l from Lugar l join Comentario  c  on l.id = c.lugarComentario.id where c.calificacion = ?1")
    List<Lugar> obtenerLugarCalificaion(int calificion);

    @Query("select  l from Lugar l join Ciudad c on l.ciudadLugar.id = c.id where c.nombre = ?1 ")
    List<Lugar> obtenerLugarPorCiudad(String nombre);

    @Query("select  l from Lugar l where l.tipoLugar.nombre= ?1")
    List<Lugar> obtenerLugarPorTipo(String tipo);

    @Query("select l.nombre, l.descripcion, l.ciudadLugar.nombre, l.tipoLugar.nombre " +
            "from Lugar l where l.moderador.cedula = ?1 and l.estado = 'APROBADO' or  l.estado='RECHAZADO'")
    List<Object[]> obtenerLugaresRevisadoModerador(String cedulaModerador);
}
