package unilocal.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unilocal.dto.LugarComentariosDTO;
import unilocal.entidades.Lugar;

import java.util.List;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    @Query("select l.id, l.nombre, l.latitud, l.longitud from Lugar l where l.id = :idLugar")
    List<Object[]> obtenerInfoLugar(@Param("idLugar") int id);

    @Query("select l.id, l.nombre, l.latitud, l.longitud from Lugar l")
    List<Object[]> obtenerInfoLugares();

    @Query("select l.nombre, l.descripcion, l.ciudadLugar.nombre, l.tipoLugar.nombre " +
            "from Lugar l where l.moderador.cedula = ?1 and l.estado = 'APROBADO'")
    List<Object[]> obtenerLugaresAprobados(String cedulaModerador);

    @Query("select new unilocal.dto.LugarComentariosDTO(l, c) from Lugar l left join l.comentarios c")
    List<LugarComentariosDTO> obtenerComentariosLugares();
}
