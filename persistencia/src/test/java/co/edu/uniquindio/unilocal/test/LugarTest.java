package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.dto.LugarComentariosDTO;
import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.repositorios.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Test que se encarga de mostrar las pruebas unitarias del
 * lugar, como lo es agregar, eliminar, actualizar y leer
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private HorarioRepo horarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private TipoLugarRepo tipoLugarRepo;

    /**
     * Test encargado de comprobar el registro de un lugar
     * con los datos necesarios como el nombre, la decripción, el tipo de lugar, la ciudad, la fecha de creacion
     * la longitud, la latitud y el estado de la aprobación para proceder
     * a guardarlo al repositorio correspondiente
     */
    @Test
    public void regitrarLugarTest() {

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Horario horario1 = new Horario("Lunes a Viernes", "14:00", "22:00");
        Horario horario2 = new Horario("Sabado", "14:00", "22:00");
        Horario horario3 = new Horario("Domingo", "14:00", "22:00");
        Horario horario4 = new Horario("Festivo", "14:00", "22:00");
        horarioRepo.save(horario1);
        horarioRepo.save(horario2);
        horarioRepo.save(horario3);
        horarioRepo.save(horario4);
        List<Horario> horarios = new ArrayList<>();
        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        horarios.add(horario4);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuarioRepo.save(usuNuevo);

        TipoLugar tipoNuevo = new TipoLugar("Cafeteria");
        tipoLugarRepo.save(tipoNuevo);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");
            Lugar lugarNuevo = new Lugar("Mocawa", "hotal de lujo", tipoNuevo,
                    ciudadNueva, fecha, 2.7777f, 134.4555f, EstadoAprobacion.PENDIENTE, usuNuevo);
            lugarNuevo.setTelefono(telefonos);
            Lugar lugarGuardado = lugarRepo.save(lugarNuevo);
            Assertions.assertNotNull(lugarGuardado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test encargado de comprobar la eliminación de un lugar
     * mediante la busqueda del mismo por el número de registro
     */

    @Test
    public void eliminarLugarTest() {

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Horario horario1 = new Horario("Lunes a Viernes", "14:00", "22:00");
        Horario horario2 = new Horario("Sabado", "14:00", "22:00");
        Horario horario3 = new Horario("Domingo", "14:00", "22:00");
        Horario horario4 = new Horario("Festivo", "14:00", "22:00");
        horarioRepo.save(horario1);
        horarioRepo.save(horario2);
        horarioRepo.save(horario3);
        horarioRepo.save(horario4);
        List<Horario> horarios = new ArrayList<>();
        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        horarios.add(horario4);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuarioRepo.save(usuNuevo);

        TipoLugar tipoNuevo = new TipoLugar("Cafeteria");
        tipoLugarRepo.save(tipoNuevo);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");
            Lugar lugarNuevo = new Lugar("Mocawa", "hotal de lujo", tipoNuevo,
                    ciudadNueva, fecha, 2.7777f, 134.4555f, EstadoAprobacion.PENDIENTE, usuNuevo);
            lugarNuevo.setTelefono(telefonos);
            lugarRepo.save(lugarNuevo);
            lugarRepo.delete(lugarNuevo);

            Lugar lugarBorrado = lugarRepo.findById(1).orElse(null);

            Assertions.assertNull(lugarBorrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test encargado de comprobar la actualización de datos de un lugar
     * en este caso la actualización del nombre, buscando el
     * lugar mediante numero de registro
     */

    @Test
    public void modificarLugarTest() {

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Horario horario1 = new Horario("Lunes a Viernes", "14:00", "22:00");
        Horario horario2 = new Horario("Sabado", "14:00", "22:00");
        Horario horario3 = new Horario("Domingo", "14:00", "22:00");
        Horario horario4 = new Horario("Festivo", "14:00", "22:00");
        horarioRepo.save(horario1);
        horarioRepo.save(horario2);
        horarioRepo.save(horario3);
        horarioRepo.save(horario4);
        List<Horario> horarios = new ArrayList<>();
        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        horarios.add(horario4);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuarioRepo.save(usuNuevo);

        TipoLugar tipoNuevo = new TipoLugar("Cafeteria");
        tipoLugarRepo.save(tipoNuevo);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");
            Lugar lugarNuevo = new Lugar("Mocawa", "hotal de lujo", tipoNuevo,
                    ciudadNueva, fecha, 2.7777f, 134.4555f, EstadoAprobacion.PENDIENTE, usuNuevo);
            lugarNuevo.setTelefono(telefonos);
            Lugar lugarGuardado = lugarRepo.save(lugarNuevo);
            lugarGuardado.setNombre("Hotel Armenia");
            lugarRepo.save(lugarNuevo);

            Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

            Assertions.assertEquals("1234", lugarBuscado.getUsuarioCreador().getCedula());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test encargado de mostrar que los lugares están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugaresTest() {

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
        for (Lugar l : lista) {
            System.out.println(l);
        }
    }

    /**
     * Imprime la info de un lugar en especifico
     */
    @Test
    @Sql("classpath:lugares.sql")
    public void infoLugarTest() {

        List<Object[]> infoLugar = lugarRepo.obtenerInfoLugar(1);

        for (int i = 0; i < infoLugar.get(0).length; i++) {
            System.out.print(infoLugar.get(0)[i] + " ");
        }
    }

    /**
     * Imprime la info de los lugares
     */
    @Test
    @Sql("classpath:lugares.sql")
    public void infoLugaresTest() {

        List<Object[]> infoLugar = lugarRepo.obtenerInfoLugares();

        for (int i = 0; i < infoLugar.size(); i++) {
            for (int j = 0; j < infoLugar.get(i).length; j++) {
                System.out.print(infoLugar.get(i)[j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void obtenerComentariosLugaresTest() {

        List<LugarComentariosDTO> infoLugar = lugarRepo.obtenerComentariosLugares();

        for (LugarComentariosDTO lc : infoLugar) {
            System.out.println(lc.getLugar().getNombre() + " - " + lc.getComentario().getMensaje());
        }
    }

}
