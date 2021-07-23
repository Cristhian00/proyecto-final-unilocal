package co.edu.uniquindio.unilocal.repositorios;


import co.edu.uniquindio.unilocal.dto.LugarComentariosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Lugar;

import java.util.Date;
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

    @Query("select l from Lugar l where l.nombre = ?1")
    Lugar obtenerLugarNombre(String nombre);

    //Lugares que esten aprobados por un moderador
    @Query("select l from Lugar l where l.nombre like concat('%', :nombre, '%') ")
    List<Lugar> buscarLugares(String nombre);

    Optional<Lugar> findByLatitudAndLongitud(double latitud, double longitud);

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
}
