package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.repositorios.DepartamentoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import co.edu.uniquindio.unilocal.entidades.Departamento;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartamentoTest {

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Test
    public void registrarDepartamentoTest() {

        Departamento depaNuevo = new Departamento("Quindio", "Colombia");

        Departamento depaGuardado = departamentoRepo.save(depaNuevo);

        Assertions.assertNotNull(depaGuardado);
    }

    /**
     * Test encargado de comprobar la eliminación de un horario
     * mediante la busqueda del mismo por el número de registro del horario
     */
    @Test
    public void eliminarDepartamentoTest() {

        Departamento depaNuevo = new Departamento("Quindio", "Colombia");

        departamentoRepo.save(depaNuevo);
        departamentoRepo.delete(depaNuevo);

        Departamento depaBorrado = departamentoRepo.findById(1).orElse(null);

        Assertions.assertNull(depaBorrado);
    }

    /**
     * Test encargado de comprobar la actualización de datos de un horario
     * en este caso la actualización de la hora de apertura, buscando el
     * horario mediante el registro del mismo
     */

    @Test
    public void modificarDepartamentoTest() {

        Departamento depaNuevo = new Departamento("Quindio", "Colombia");

        Departamento depaGuardado = departamentoRepo.save(depaNuevo);
        depaGuardado.setNombre("Risaralda");
        departamentoRepo.save(depaGuardado);

        Departamento depaBuscado = departamentoRepo.findById(1).orElse(null);

        Assertions.assertEquals("Risaralda", depaBuscado.getNombre());
    }

    /**
     * Test encargado de mostrar que los horarios están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:departamentos.sql")
    public void listarDepartamentos() {

        /*
        Departamento depaNuevo = new Departamento("Quindio","Colombia");
        departamentoRepo.save(depaNuevo);
        Departamento depaNuevo2 = new Departamento("Risaralda","Colombia");
        departamentoRepo.save(depaNuevo2);
        Departamento depaNuevo3 = new Departamento("Cundinamarca","Colombia");
        departamentoRepo.save(depaNuevo3);
         */

        List<Departamento> lista = departamentoRepo.findAll();
        for (Departamento d : lista) {
            System.out.println(d);
        }
    }
}
