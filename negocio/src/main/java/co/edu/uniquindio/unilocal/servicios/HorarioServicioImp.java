package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.repositorios.HorarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioServicioImp implements HorarioServicio {

    private final HorarioRepo horarioRepo;

    public HorarioServicioImp(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }

    public boolean horarioDisponible(Horario h) {
        Optional<Horario> horario =
                horarioRepo.obtenerHorario(h.getDia(), h.getHoraApertura(), h.getHoraCierre());
        return horario.isEmpty();
    }

    @Override
    public Horario registrarHorario(Horario h) throws Exception {

        if (h.getDia() == null) {
            throw new Exception("Debe seleccionar un día para el horario");
        }
        if (h.getHoraApertura() == null) {
            throw new Exception("Debe seleccionar una hora de apertura valida");
        }
        if (h.getHoraApertura().length() > 5) {
            throw new Exception("La hora de apertura debe tener máximo 5 caracteres");
        }
        if (h.getHoraCierre() == null) {
            throw new Exception("Debe seleccionar una hora de cierre valida");
        }
        if (h.getHoraCierre().length() > 5) {
            throw new Exception("La hora de cierre debe tener máximo 5 caracteres");
        }
        return horarioRepo.save(h);
    }

    @Override
    public Horario modificarHorario(Horario h) throws Exception {

        if(h==null){
            throw new Exception("Debe haber un horario para modificar");
        }
        Optional<Horario> horario = horarioRepo.findByCodigo(h.getCodigo());
        if (horario.isEmpty()) {
            throw new Exception("No hay horario con ese ID registrado");
        }
        if (h.getDia() == null) {
            throw new Exception("Debe seleccionar un día para el horario");
        }
        if (h.getHoraApertura() == null) {
            throw new Exception("Debe seleccionar una hora de apertura valida");
        }
        if (h.getHoraApertura().length() > 5) {
            throw new Exception("La hora de apertura debe tener máximo 5 caracteres");
        }
        if (h.getHoraCierre() == null) {
            throw new Exception("Debe seleccionar una hora de cierre valida");
        }
        if (h.getHoraCierre().length() > 5) {
            throw new Exception("La hora de cierre debe tener máximo 5 caracteres");
        }
        if (!horario.get().getDia().equals(h.getDia()) && !horario.get().getHoraApertura().equals(h.getHoraApertura())
                && !horario.get().getHoraCierre().equals(h.getHoraCierre())) {
            if(!horarioDisponible(h)){
                throw new Exception("Ya existe un hoario con los mismos datos");
            }

        }
        return horarioRepo.save(h);
    }

    @Override
    public boolean eliminarHorario(int codigo) throws Exception {
        Optional<Horario> h = horarioRepo.findByCodigo(codigo);
        if(h.isEmpty()){
            throw new Exception("No hay ningún horario registrado con ese ID");
        }
        horarioRepo.delete(h.get());
        return true;
    }

    @Override
    public Horario obtenerHorario(int codigo) throws Exception {
        Optional<Horario> h = horarioRepo.findByCodigo(codigo);
        if(h.isEmpty()){
            throw new Exception("No hay ningún horario registrado con ese ID");
        }
        return h.get();
    }

    @Override
    public List<Horario> listarHorario() {
        return horarioRepo.findAll();
    }
}
