package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.*;
import unilocal.repositorios.LugarRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    @Autowired
    private LugarRepo lugarRepo;

    @Test
    public void regitrarLugarTest() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");
            Lugar lugarNuevo = new Lugar("Mocawa", "hotal de lujo", TipoLugar.HOTEL,
                    Ciudad.ARMENIA, fecha, 2.7777, 134.4555, EstadoAprobacion.PENDIENTE);

            Lugar lugarGuardado = lugarRepo.save(lugarNuevo);
            Assertions.assertNotNull(lugarGuardado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarLugarTest() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");
            Lugar lugarNuevo = new Lugar("Mocawa", "hotal de lujo", TipoLugar.HOTEL,
                    Ciudad.ARMENIA, fecha, 2.7777, 134.4555, EstadoAprobacion.PENDIENTE);

            lugarRepo.save(lugarNuevo);
            lugarRepo.delete(lugarNuevo);

            Lugar lugarBorrado = lugarRepo.findById(1).orElse(null);

            Assertions.assertNull(lugarBorrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modificarLugarTest() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");
            Lugar lugarNuevo = new Lugar("Mocawa", "hotal de lujo", TipoLugar.HOTEL,
                    Ciudad.ARMENIA, fecha, 2.7777, 134.4555, EstadoAprobacion.PENDIENTE);

            Lugar lugarGuardado = lugarRepo.save(lugarNuevo);
            lugarGuardado.setNombre("Hotel Armenia");
            lugarRepo.save(lugarNuevo);

            Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

            Assertions.assertEquals("1234", lugarBuscado.getUsuarioLugar().getCedula());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugares() {

        /*
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");

            Lugar lugarNuevo1 = new Lugar("Mocawa", "Hotel de lujo", TipoLugar.HOTEL,
                    Ciudad.ARMENIA, fecha, 4.5499102, -75.6617799, EstadoAprobacion.APROBADO);
            Lugar lugarNuevo2 = new Lugar("Hotel Armenia", "Hospedaje barato", TipoLugar.HOTEL,
                    Ciudad.ARMENIA, fecha, 4.549028, -75.6623743, EstadoAprobacion.PENDIENTE);
            Lugar lugarNuevo3 = new Lugar("Salome Café", "Café bar", TipoLugar.CAFETERIA,
                    Ciudad.MONTENEGRO, fecha, 4.5671406, -75.7502047, EstadoAprobacion.RECHAZADO);
            Lugar lugarNuevo4 = new Lugar("Mc Nicko Burguer", "hotal de lujo", TipoLugar.COMIDA_RAPIDA,
                    Ciudad.QUIMBAYA, fecha, 4.6213931, -75.7649512, EstadoAprobacion.PENDIENTE);

            lugarRepo.save(lugarNuevo1);
            lugarRepo.save(lugarNuevo2);
            lugarRepo.save(lugarNuevo3);
            lugarRepo.save(lugarNuevo4);

        } catch (Exception e) {
            e.printStackTrace();
        }
         */

        List<Lugar> lista = lugarRepo.findAll();
        System.out.println(lista);
    }

}
