package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.repositorios.ComentarioRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImp implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImp(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }


    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {

        if (c.getMensaje() == null) {
            throw new Exception("Debe ingresar un mensaje como comentario");
        }
        if (c.getMensaje().length() > 255) {
            throw new Exception("El mensaje del comentario debe tener máximo 255 caracteres");
        }
        if (c.getCalificacion() < 0) {
            throw new Exception("La calificación debe ser mínimo 0");
        }
        if (c.getCalificacion() > 5) {
            throw new Exception("La calificación debe ser máximo 5");
        }
        c.setFechaComentario(new Date());
        return comentarioRepo.save(c);
    }

    @Override
    public Comentario modificarComentario(Comentario c) throws Exception {

        Optional<Comentario> comen = comentarioRepo.findById(c.getId());
        if(comen.isEmpty()){
            throw new Exception("No existe un comentario con ese número ID");
        }
        if (c.getMensaje() == null) {
            throw new Exception("Debe ingresar un mensaje como comentario");
        }
        if (c.getMensaje().length() > 255) {
            throw new Exception("El mensaje del comentario debe tener máximo 255 caracteres");
        }
        if (c.getCalificacion() < 0) {
            throw new Exception("La calificación debe ser mínimo 0");
        }
        if (c.getCalificacion() > 5) {
            throw new Exception("La calificación debe ser máximo 5");
        }
        c.setFechaComentario(new Date());
        return comentarioRepo.save(c);
    }

    @Override
    public boolean eliminarComentario(int id) throws Exception {

        Optional<Comentario> comen = comentarioRepo.findById(id);
        if(comen.isEmpty()){
            throw new Exception("No existe un comentario con ese número ID");
        }
        comentarioRepo.delete(comen.get());
        return true;
    }

    @Override
    public Comentario obtenerComentario(int id) throws Exception {

        Optional<Comentario> c = comentarioRepo.findById(id);
        if(c.isEmpty()){
            throw new Exception("No existe ningún comentario con ese ID");
        }
        return c.get();
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }
}
