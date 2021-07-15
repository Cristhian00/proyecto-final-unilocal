package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.repositorios.DepartamentoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServicioImp implements DepartamentoServicio{

    private final DepartamentoRepo departamentoRepo;

    public DepartamentoServicioImp(DepartamentoRepo departamentoRepo) {
        this.departamentoRepo = departamentoRepo;
    }

    @Override
    public Departamento registrarDepartamento(Departamento d) throws Exception {
        return departamentoRepo.save(d);
    }

    @Override
    public Departamento modificarDepartamento(Departamento d) throws Exception {
        return departamentoRepo.save(d);
    }

    @Override
    public boolean eliminarDepartamento(Departamento d) throws Exception {
        departamentoRepo.delete(d);
        return true;
    }

    @Override
    public Departamento obtenerDepartamento(String nombre) throws Exception {
        return departamentoRepo.findByNombre(nombre);
    }

    @Override
    public List<Departamento> listarDepartamento() {
        return departamentoRepo.findAll();
    }
}
