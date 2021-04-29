package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.TipoLugar;
import unilocal.repositorios.TipoLugarRepo;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoLugarTest {

    @Autowired
    private TipoLugarRepo tipoLugarRepo;

    @Test
    public void registrarTipoTest(){

        TipoLugar tipoNuevo = new TipoLugar("Cafeteria");
        TipoLugar tipoGuardado = tipoLugarRepo.save(tipoNuevo);

        Assertions.assertNotNull(tipoGuardado);
    }

    @Test
    public void eliminarTipoTest(){

        TipoLugar tipoNuevo = new TipoLugar("Cafeteria");
        tipoLugarRepo.save(tipoNuevo);
        tipoLugarRepo.delete(tipoNuevo);

        TipoLugar tipoBorrado = tipoLugarRepo.findById(1).orElse(null);
        Assertions.assertNull(tipoBorrado);
    }

    @Test
    public void modificarTipoTest(){

        TipoLugar tipoNuevo = new TipoLugar("Cafeteria");
        TipoLugar tipoGuardado = tipoLugarRepo.save(tipoNuevo);
        tipoGuardado.setNombre("Hotel");
        tipoLugarRepo.save(tipoGuardado);

        TipoLugar tipoBuscado = tipoLugarRepo.findById(1).orElse(null);

        Assertions.assertEquals("Hotel", tipoBuscado.getNombre());

    }

    @Test
    @Sql("classpath:tiposlugar.sql")
    public void listarTipos(){

        List<TipoLugar> lista = tipoLugarRepo.findAll();
        for (TipoLugar t: lista){
            System.out.println(t);
        }
    }
}
