package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import co.edu.uniquindio.unilocal.repositorios.DepartamentoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Test
    public void registrarCiudadTest() {

        Departamento depar = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(depar);

        Ciudad ciudadNueva = new Ciudad("Armenia", depar);

        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);

        Assertions.assertNotNull(ciudadGuardada);
    }

    /**
     * Test encargado de comprobar la eliminación de un horario
     * mediante la busqueda del mismo por el número de registro del horario
     */
    @Test
    public void eliminarCiudadTest() {

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);

        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);
        ciudadRepo.delete(ciudadNueva);

        Ciudad ciudadBorrado = ciudadRepo.findById(1).orElse(null);

        Assertions.assertNull(ciudadBorrado);
    }

    /**
     * Test encargado de comprobar la actualización de datos de un horario
     * en este caso la actualización de la hora de apertura, buscando el
     * horario mediante el registro del mismo
     */
    @Test
    public void modificarCiudadTest() {

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);

        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        Ciudad ciudadGuardada = ciudadRepo.save(ciudadNueva);

        ciudadGuardada.setNombre("Montenegro");
        ciudadRepo.save(ciudadGuardada);

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);

        Assertions.assertEquals("Montenegro", ciudadBuscada.getNombre());
    }

    /**
     * Test encargado de mostrar que los horarios están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:ciudades.sql")
    public void listarCiudades() {

        /*
        Departamento departamento1 = new Departamento("Quindio","Colombia");
        departamentoRepo.save(departamento1);
        Departamento departamento2 = new Departamento("Cundinamarca","Colombia");
        departamentoRepo.save(departamento2);

        Ciudad ciudadNueva1 = new Ciudad("Armenia", departamento1);
        ciudadRepo.save(ciudadNueva1);
        Ciudad ciudadNueva2 = new Ciudad("Circasia", departamento1);
        ciudadRepo.save(ciudadNueva2);
        Ciudad ciudadNueva3 = new Ciudad("Bogota DC", departamento2);
        ciudadRepo.save(ciudadNueva3);
        Ciudad ciudadNueva4 = new Ciudad("Mesitas", departamento2);
        ciudadRepo.save(ciudadNueva4);
         */

        List<Ciudad> lista = ciudadRepo.findAll();
        for (Ciudad c : lista) {
            System.out.println(c);
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarUsuariosTest() {

        List<Usuario> usuarios = ciudadRepo.obtenerUsuarios("Armenia");

        for (Usuario u : usuarios) {
            System.out.println(u.getNombre());
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarUsuariosLeftJoinTest() {

        List<Object[]> usuarios = ciudadRepo.obtenerUsuariosLeftJoin();

        for (Object[] obj : usuarios) {
            System.out.println(obj[0] + " - " + obj[1]);
        }
    }
}
