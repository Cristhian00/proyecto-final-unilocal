package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImp implements CiudadServicio {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImp(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    public boolean ciudadDisponible(Ciudad c) {
        Optional<Ciudad> ciudad = ciudadRepo.obtenerCiudadNombreDepartamento(c.getNombre(), c.getDepartamento().getNombre());
        return ciudad.isEmpty();
    }

    @Override
    public Ciudad registrarCiudad(Ciudad c) throws Exception {

        if (c.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre de ciudad");
        }
        if (c.getNombre().length() > 100) {
            throw new Exception("El nombre de la ciudad no puede tener más de 100 caracteres");
        }
        if (c.getDepartamento() == null) {
            throw new Exception("Debe seleccionar un departemento");
        }
        if (!ciudadDisponible(c)) {
            throw new Exception("La ciudad ya se encuentra registrada");
        }
        return ciudadRepo.save(c);
    }

    @Override
    public Ciudad modificarCiudad(Ciudad c) throws Exception {

        if (c.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre de ciudad");
        }
        if (c.getNombre().length() > 100) {
            throw new Exception("El nombre de la ciudad no puede tener más de 100 caracteres");
        }
        if (c.getDepartamento() == null) {
            throw new Exception("Debe seleccionar un departemento");
        }
        Optional<Ciudad> ciudad = ciudadRepo.findById(c.getId());
        if (!ciudad.get().getNombre().equals(c.getNombre()) &&
                !ciudad.get().getDepartamento().getNombre().equals(c.getDepartamento().getNombre())) {
            if (!ciudadDisponible(c)) {
                throw new Exception("La ciudad con esos datos ya se encuentra registrada");
            }
        }
        return ciudadRepo.save(c);
    }

    @Override
    public boolean eliminarCiudad(int id) throws Exception {

        Optional<Ciudad> ciudad = ciudadRepo.findById(id);
        if (ciudad.isEmpty()) {
            throw new Exception("No existe ninguna ciudad con ese ID");
        }
        ciudadRepo.delete(ciudad.get());
        return true;
    }

    @Override
    public Ciudad obtenerCiudad(String nombre) throws Exception {

        if (nombre == null) {
            throw new Exception("Debe ingresar un nombre de ciudad");
        }
        Optional<Ciudad> ciudad = ciudadRepo.findByNombre(nombre);
        if (ciudad.isEmpty()) {
            throw new Exception("No existe una ciudad registrada con ese nombre");
        }
        return ciudad.get();
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws Exception {
        Optional<Ciudad> ciudad = ciudadRepo.findById(id);
        if (ciudad.isEmpty()) {
            throw new Exception("No existe una ciudad resgitrada con ese ID");
        }
        return ciudad.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    public List<Ciudad> listarCiudadesOrdenadas(){

        return ciudadRepo.listarCiudadesOrdenadas();
    }
}
