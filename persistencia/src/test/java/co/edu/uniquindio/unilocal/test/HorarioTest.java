package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.entidades.Horario;
import co.edu.uniquindio.unilocal.repositorios.HorarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Test que se encarga de mostrar las pruebas unitarias del
 * horario que maneja un lugar, como lo es agregar, eliminar, actualizar y leer
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;

    /**
     * Test encargado de comprobar el registro de un horario
     * con los datos necesarios como el dia de la semana, la hora de apertura
     * y la hora de cierre del lugar para proceder a guardarlo al repositorio correspondiente
     */

    @Test
    public void registrarHorarioTest() {

        Horario horarioNuevo = new Horario("Lunes", "14:00", "22:00");

        Horario horarioGuardado = horarioRepo.save(horarioNuevo);

        Assertions.assertNotNull(horarioGuardado);
    }

    /**
     * Test encargado de comprobar la eliminación de un horario
     * mediante la busqueda del mismo por el número de registro del horario
     */

    @Test
    public void eliminarHorarioTest() {

        Horario horarioNuevo = new Horario("Lunes", "14:00", "22:00");

        horarioRepo.save(horarioNuevo);
        horarioRepo.delete(horarioNuevo);

        Horario horarioBorrado = horarioRepo.findById(1).orElse(null);

        Assertions.assertNull(horarioBorrado);
    }

    /**
     * Test encargado de comprobar la actualización de datos de un horario
     * en este caso la actualización de la hora de apertura, buscando el
     * horario mediante el registro del mismo
     */

    @Test
    public void modificarHorarioTest() {

        Horario horarioNuevo = new Horario("Lunes", "14:00", "22:00");

        Horario horarioGuardado = horarioRepo.save(horarioNuevo);
        horarioGuardado.setHoraApertura("13:00");
        horarioRepo.save(horarioGuardado);

        Horario horarioBuscado = horarioRepo.findById(1).orElse(null);

        Assertions.assertEquals("13:00", horarioBuscado.getHoraApertura());
    }

    /**
     * Test encargado de mostrar que los horarios están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:horarios.sql")
    public void listarHorarios() {

        /*
        Horario horarioNuevo1 = new Horario(DiaSemana.LUNES, "14:00", "22:00");
        horarioRepo.save(horarioNuevo1);
        Horario horarioNuevo2 = new Horario(DiaSemana.MARTES, "14:00", "22:00");
        horarioRepo.save(horarioNuevo2);
        Horario horarioNuevo3 = new Horario(DiaSemana.MIERCOLES, "14:00", "22:00");
        horarioRepo.save(horarioNuevo3);
        Horario horarioNuevo4 = new Horario(DiaSemana.JUEVES, "14:00", "22:00");
        horarioRepo.save(horarioNuevo4);
        */

        List<Horario> lista = horarioRepo.findAll();
        for (Horario h : lista) {
            System.out.println(h);
        }
    }
}
