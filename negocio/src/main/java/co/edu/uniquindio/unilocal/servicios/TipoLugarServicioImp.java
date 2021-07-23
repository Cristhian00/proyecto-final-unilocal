package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import co.edu.uniquindio.unilocal.repositorios.TipoLugarRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoLugarServicioImp implements TipoLugarServicio {

    private final TipoLugarRepo tipoLugarRepo;

    public TipoLugarServicioImp(TipoLugarRepo tipoLugarRepo) {
        this.tipoLugarRepo = tipoLugarRepo;
    }

    @Override
    public TipoLugar registrarTipoLugar(TipoLugar t) throws Exception {

        if (t.getNombre() == null) {
            throw new Exception("Debe ingresar el nombre del tipo de lugar");
        }
        if (t.getNombre().length() > 50) {
            throw new Exception("El nombre del tipo debe tener máximo 50 caracteres");
        }
        Optional<TipoLugar> tipo = tipoLugarRepo.findByNombre(t.getNombre());
        if (!tipo.isEmpty()) {
            throw new Exception("El tipo de lugar ya se encuentra registrado");
        }
        return tipoLugarRepo.save(t);
    }

    @Override
    public TipoLugar modificarTipoLugar(TipoLugar t) throws Exception {

        if (t.getNombre() == null) {
            throw new Exception("Debe ingresar el nombre del tipo de lugar");
        }
        if (t.getNombre().length() > 50) {
            throw new Exception("El nombre del tipo debe tener máximo 50 caracteres");
        }
        Optional<TipoLugar> tipo = tipoLugarRepo.findById(t.getId());
        if (tipo.isEmpty()) {
            throw new Exception("El tipo con ese ID no se encuentra registrado");
        }
        if(!tipo.get().getNombre().equals(t.getNombre())){
            tipo = tipoLugarRepo.findByNombre(t.getNombre());
            if(!tipo.isEmpty()){
                throw new Exception("Ya un tipo de lugar con ese nombre");
            }
        }
        return tipoLugarRepo.save(t);
    }

    @Override
    public boolean eliminarTipoLugar(String nombre) throws Exception {

        if(nombre == null){
            throw new Exception("Debe ingresar el nombre del tipo que desea eliminar");
        }
        Optional<TipoLugar> tipo = tipoLugarRepo.findByNombre(nombre);
        if(tipo.isEmpty()){
            throw new Exception("No hay ningún tipo de lugar registrado con ese nombre");
        }
        tipoLugarRepo.delete(tipo.get());
        return true;
    }

    @Override
    public TipoLugar obtenerTipoLugar(int id) throws Exception {

        Optional<TipoLugar> objeto = tipoLugarRepo.findById(id);
        if (objeto.isEmpty()) {
            throw new Exception("El id no esta registrado en ningún tipo de lugar");
        }
        return objeto.get();
    }

    @Override
    public TipoLugar obtenerTipoLugar(String nombre) throws Exception {

        Optional<TipoLugar> objeto = tipoLugarRepo.findByNombre(nombre);
        if (objeto.isEmpty()) {
            throw new Exception("El nombre no esta registrado en ningún tipo de lugar");
        }
        return objeto.get();
    }

    @Override
    public List<TipoLugar> listarTipoLugar() {
        return tipoLugarRepo.findAll();
    }
}
