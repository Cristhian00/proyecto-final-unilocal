package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.repositorios.DepartamentoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServicioImp implements DepartamentoServicio {

    private final DepartamentoRepo departamentoRepo;

    public DepartamentoServicioImp(DepartamentoRepo departamentoRepo) {
        this.departamentoRepo = departamentoRepo;
    }

    public boolean departamentoDisponible(Departamento d) {

        Optional<Departamento> depart = departamentoRepo.obtenerDepartamentoNombrePais(d.getNombre(), d.getPais());
        return depart.isEmpty();
    }

    @Override
    public Departamento registrarDepartamento(Departamento d) throws Exception {

        if (d.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre de departamento");
        }
        if (d.getNombre().length() > 100) {
            throw new Exception("El nombre del departamento no puede tener más de 100 caracteres");
        }
        if (d.getPais() == null) {
            throw new Exception("Debe seleccionar un pais");
        }
        if (!departamentoDisponible(d)) {
            throw new Exception("El departamento ya se encuentra registrado");
        }
        return departamentoRepo.save(d);
    }

    @Override
    public Departamento modificarDepartamento(Departamento d) throws Exception {

        if (d.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre de departamento");
        }
        if (d.getNombre().length() > 100) {
            throw new Exception("El nombre del departamento no puede tener más de 100 caracteres");
        }
        if (d.getPais() == null) {
            throw new Exception("Debe seleccionar un pais");
        }
        Optional<Departamento> depart = departamentoRepo.findById(d.getId());
        if (!depart.get().getNombre().equals(d.getNombre()) &&
                !depart.get().getPais().equals(d.getPais())) {
            if (!departamentoDisponible(d)) {
                throw new Exception("El departamento con esos datos ya se encuentra registrado");
            }
        }
        return departamentoRepo.save(d);
    }

    @Override
    public boolean eliminarDepartamento(int id) throws Exception {
        Optional<Departamento> depart = departamentoRepo.findById(id);
        if (depart.isEmpty()) {
            throw new Exception("No existe ninguna ciudad con ese ID");
        }
        departamentoRepo.delete(depart.get());
        return true;
    }

    @Override
    public Departamento obtenerDepartamento(String nombre) throws Exception {

        if (nombre == null) {
            throw new Exception("Debe ingresar un nombre de departamento");
        }
        Optional<Departamento> depart = departamentoRepo.findByNombre(nombre);
        if (depart.isEmpty()) {
            throw new Exception("No existe un departamento registrado con ese nombre");
        }
        return depart.get();
    }

    @Override
    public Departamento obtenerDepartamento(int id) throws Exception {

        Optional<Departamento> depart = departamentoRepo.findById(id);
        if (depart.isEmpty()) {
            throw new Exception("No existe un departamento registrado con ese ID");
        }
        return depart.get();
    }

    @Override
    public List<Departamento> listarDepartamento() {
        return departamentoRepo.findAll();
    }
}
