package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.repositorios.ComentarioRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComentarioServicioImp implements ComentarioServicio{

    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImp(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {
        return comentarioRepo.save(c);
    }

    @Override
    public Comentario modificarComentario(Comentario c) throws Exception {
        return comentarioRepo.save(c);
    }

    @Override
    public boolean eliminarComentario(Comentario c) throws Exception {
        comentarioRepo.delete(c);
        return true;
    }

    @Override
    public Comentario obtenerComentario(String cedula, int id, Date fecha) throws Exception {
        return comentarioRepo.obtenerComentario(cedula, id, fecha);
    }

    @Override
    public List<Comentario> listarDepartamento() {
        return null;
    }
}
