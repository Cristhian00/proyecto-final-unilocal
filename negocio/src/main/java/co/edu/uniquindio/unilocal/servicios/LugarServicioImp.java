package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.repositorios.ComentarioRepo;
import co.edu.uniquindio.unilocal.repositorios.LugarRepo;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LugarServicioImp implements LugarServicio {

    private final LugarRepo lugarRepo;
    private final ComentarioRepo comentarioRepo;
    private final UsuarioRepo usuarioRepo;

    public LugarServicioImp(LugarRepo lugarRepo, ComentarioRepo comentarioRepo, UsuarioRepo usuarioRepo) {
        this.lugarRepo = lugarRepo;
        this.comentarioRepo = comentarioRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public boolean idDisponible(int id) {
        Optional<Lugar> lugar = lugarRepo.findById(id);
        return lugar.isEmpty();
    }

    public boolean nombreDisponible(String nombre) {
        Optional<Lugar> lugar = lugarRepo.findByNombre(nombre);
        return lugar.isEmpty();
    }

    public boolean ubicacionDisponible(Float latitud, Float longitud) {
        Optional<Lugar> lugar = lugarRepo.findByLatitudAndLongitud(latitud, longitud);
        return lugar.isEmpty();
    }

    @Override
    public Lugar registrarLugar(Lugar l) throws Exception {

        //Validaciones del nombre
        if (l.getNombre().isEmpty()) {
            throw new Exception("Debe ingresar un nombre");
        }
        if (!nombreDisponible(l.getNombre())) {
            throw new Exception("El nombre ya se encuentra registrado en otro lugar");
        }
        if (l.getNombre().length() > 100) {
            throw new Exception("El nombre del lugar no puede tener más de 100 caracteres");
        }
        //Validaciones de la ubicacion
        if (l.getLatitud() == 0.0F) {
            throw new Exception("Debe ingresar una latitud");
        }
        if (l.getLongitud() == 0.0F) {
            throw new Exception("Debe ingresar una longitud");
        }
        if (!ubicacionDisponible(l.getLatitud(), l.getLongitud())) {
            throw new Exception("Ya hay un lugar registrado con la misma ubicación (latitud y longitud)");
        }
        //Resto de validaciones
        if (l.getDescripcion().isEmpty()) {
            throw new Exception("Debe ingresar una descripción");
        }
        if (l.getDescripcion().length() > 255) {
            throw new Exception("La descripción no puede tener más de 255 caracteres");
        }
        l.setEstado(EstadoAprobacion.PENDIENTE);
        l.setFechaCreacion(new Date());

        Lugar lugarNew = lugarRepo.save(l);
        return lugarNew;
    }

    @Override
    public Lugar modificarLugar(Lugar l) throws Exception {

        if (idDisponible(l.getId())) {
            throw new Exception("El id del lugar no se encuentra registrado");
        }
        Lugar lugar = lugarRepo.obtenerLugar(l.getId());
        //Validaciones del nombre
        if (l.getNombre().isEmpty()) {
            throw new Exception("Debe ingresar un nombre");
        }
        if (!lugar.getNombre().equals(l.getNombre())) {
            if (!nombreDisponible(l.getNombre())) {
                throw new Exception("El nombre ya se encuentra registrado en otro lugar");
            }
        }
        if (l.getNombre().length() > 100) {
            throw new Exception("El nombre no puede tener más de 100 caracteres");
        }
        //Validaciones de la ubicacion
        if (l.getLatitud() == 0.0d) {
            throw new Exception("Debe ingresar una latitud");
        }
        if (l.getLongitud() == 0.0d) {
            throw new Exception("Debe ingresar una longitud");
        }
        if (!(lugar.getLatitud() == l.getLatitud() && lugar.getLongitud() == l.getLongitud())) {
            if (ubicacionDisponible(l.getLatitud(), l.getLongitud())) {
                throw new Exception("Ya hay un lugar registrado con esa ubicción");
            }
        }
        if (l.getDescripcion().isEmpty()) {
            throw new Exception("Debe ingresar una descrición");
        }
        if (l.getDescripcion().length() > 255) {
            throw new Exception("La descripción no debe tener más de 255 caracteres");
        }
        Lugar lugarNew = lugarRepo.save(l);
        return lugarNew;
    }

    @Override
    public boolean eliminarLugar(String nombre) throws Exception {

        if (nombre.isEmpty()) {
            throw new Exception("Debe ingresar el nombre de un lugar");
        }
        if (nombreDisponible(nombre)) {
            throw new Exception("El nombre no se encuentra registrado");
        }
        Lugar lugar = obtenerLugar(nombre);
        lugarRepo.delete(lugar);

        return true;
    }

    @Override
    public Lugar obtenerLugar(int id) throws Exception {

        Optional<Lugar> l = lugarRepo.findById(id);
        if (l.isEmpty()) {
            throw new Exception("El id del lugar no se encuentra registrado");
        }
        return l.get();
    }

    @Override
    public Lugar obtenerLugar(String nombre) throws Exception {

        if (nombre != null && !nombre.isEmpty()) {
            throw new Exception("Debe ingresar el nombre del lugar");
        }
        if (nombreDisponible(nombre)) {
            throw new Exception("El nombre del lugar no se encuentra registrado");
        }
        return lugarRepo.obtenerLugarNombre(nombre);
    }

    @Override
    public List<Lugar> listarLugar() {
        return lugarRepo.findAll();
    }

    @Override
    public List<Lugar> buscarLugaresPorPalabra(String nombre) {
        return lugarRepo.buscarLugares(nombre);
    }

    @Override
    public List<Lugar> lugaresPorModerador(String moderador) {
        return lugarRepo.obtenerLugaresModerador(moderador);
    }

    @Override
    public List<Comentario> obtenerComentarios(Integer idLugar) {
        return lugarRepo.obtenerComentarios(idLugar);
    }

    @Override
    public List<Horario> obtenerHorarios(Integer idLugar) {
        return lugarRepo.obtenerHorarios(idLugar);
    }

    @Override
    public List<Lugar> obtenerLugaresPendientes() {
        return lugarRepo.obtenerLugaresPendientes();
    }

    @Override
    public boolean obtenerUsuarioFavorito(int id, String cedula) {

        Optional<Usuario> usu = lugarRepo.obtenerUsuarioFavorito(id, cedula);
        return (usu.isEmpty()) ? false : true;
    }

}
