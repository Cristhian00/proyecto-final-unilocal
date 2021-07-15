package co.edu.uniquindio.unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.entidades.Usuario;

import java.util.Date;
import java.util.List;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    List<Comentario> findAll();

    @Query("select c from Comentario c where c.usuarioComentario.cedula = ?1 and c.lugarComentario.id = ?2 " +
            "and c.fechaComentario = ?3")
    Comentario obtenerComentario(String cedula, int id, Date fecha);

    //Obtiene los comentarios que tengan una calificación mayor a la que se manda por parametro
    @Query("select c from Comentario c where c.calificacion > ?1")
    List<Comentario> obtenerListaPorCalificacion(double calificaion);

    @Query("select distinct c.usuarioComentario from Comentario c where c.lugarComentario.id = ?1")
    List<Usuario> obetenerComentariosLugares(int idLugar);
}
