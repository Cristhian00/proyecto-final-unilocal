package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import co.edu.uniquindio.unilocal.repositorios.TipoLugarRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoLugarServicioImp implements TipoLugarServicio{

    private final TipoLugarRepo tipoLugarRepo;

    public TipoLugarServicioImp(TipoLugarRepo tipoLugarRepo) {
        this.tipoLugarRepo = tipoLugarRepo;
    }

    @Override
    public TipoLugar registrarTipoLugar(TipoLugar t) throws Exception {
        return tipoLugarRepo.save(t);
    }

    @Override
    public TipoLugar modificarTipoLugar(TipoLugar t) throws Exception {
        return tipoLugarRepo.save(t);
    }

    @Override
    public boolean eliminarTipoLugar(TipoLugar t) throws Exception {
        tipoLugarRepo.delete(t);
        return true;
    }

    @Override
    public TipoLugar obtenerTipoLugar(int id) throws Exception {

        Optional<TipoLugar> objeto = tipoLugarRepo.obtenerTipoLugar(id);

        if(objeto.isEmpty()){
            throw new Exception("El id no esta registrado en ningún tipo de lugar");
        }
        return objeto.get();
    }

    @Override
    public List<TipoLugar> listarTipoLugar() {
        return tipoLugarRepo.findAll();
    }
}
