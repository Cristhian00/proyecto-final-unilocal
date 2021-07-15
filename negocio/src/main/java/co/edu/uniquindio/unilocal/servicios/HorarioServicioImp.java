package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.repositorios.HorarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioServicioImp implements HorarioServicio{

    private final HorarioRepo horarioRepo;

    public HorarioServicioImp(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }

    @Override
    public Horario registrarHorario(Horario h) throws Exception {
        return horarioRepo.save(h);
    }

    @Override
    public Horario modificarHorario(Horario h) throws Exception {
        return horarioRepo.save(h);
    }

    @Override
    public boolean eliminarHorario(Horario h) throws Exception {
        horarioRepo.delete(h);
        return true;
    }

    @Override
    public Horario obtenerHorario(int codigo) throws Exception {
        return horarioRepo.findByCodigo(codigo);
    }

    @Override
    public List<Horario> listarHorario() {
        return horarioRepo.findAll();
    }
}
