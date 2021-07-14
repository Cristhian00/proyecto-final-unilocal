package unilocal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unilocal.entidades.Comentario;
import unilocal.entidades.Usuario;

import java.util.List;

/**
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    //Obtiene los comentarios que tengan una calificación mayor a la que se manda por parametro
    @Query("select c from Comentario c where c.calificacion > ?1")
    List<Comentario> obtenerListaPorCalificacion(double calificaion);

    @Query("select distinct c.usuarioComentario from Comentario c where c.lugarComentario.id = ?1")
    List<Usuario> obetenerComentariosLugares(int idLugar);
}
