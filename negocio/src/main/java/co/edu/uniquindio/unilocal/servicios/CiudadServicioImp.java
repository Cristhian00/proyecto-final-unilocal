package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicioImp implements CiudadServicio {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImp(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Ciudad registrarCiudad(Ciudad c) throws Exception {
        return ciudadRepo.save(c);
    }

    @Override
    public Ciudad modificarCiudad(Ciudad c) throws Exception {
        return ciudadRepo.save(c);
    }

    @Override
    public boolean eliminarCiudad(Ciudad c) throws Exception {
        ciudadRepo.delete(c);
        return true;
    }

    @Override
    public Ciudad obtenerCiudad(String nombre) throws Exception {
        return ciudadRepo.findByNombre(nombre);
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}
