package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.DiaSemana;
import unilocal.entidades.Horario;
import unilocal.repositorios.HorarioRepo;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;

    @Test
    public void registrarHorarioTest(){

        Horario horarioNuevo = new Horario(DiaSemana.LUNES, "14:00", "22:00");

        Horario horarioGuardado = horarioRepo.save(horarioNuevo);

        Assertions.assertNotNull(horarioGuardado);
    }

    @Test
    public void eliminarHorarioTest(){

        Horario horarioNuevo = new Horario(DiaSemana.LUNES, "14:00", "22:00");

        horarioRepo.save(horarioNuevo);
        horarioRepo.delete(horarioNuevo);

        Horario horarioBorrado =  horarioRepo.findById(1).orElse(null);

        Assertions.assertNull(horarioBorrado);
    }

    @Test
    public void modificarUsuarioTest(){

        Horario horarioNuevo = new Horario(DiaSemana.LUNES, "14:00", "22:00");

        Horario horarioGuardado = horarioRepo.save(horarioNuevo);
        horarioGuardado.setHoraApertura("13:00");
        horarioRepo.save(horarioGuardado);

        Horario horarioBuscado =  horarioRepo.findById(1).orElse(null);

        Assertions.assertEquals("13:00", horarioBuscado.getHoraApertura());
    }

    @Test
    @Sql("classpath:horarios.sql")
    public void listarUsuarios(){

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
        System.out.println(lista);
    }
}
