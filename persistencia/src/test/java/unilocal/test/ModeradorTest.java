package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.Moderador;
import unilocal.repositorios.ModeradorRepo;

import java.util.List;
/**
 * Test que se encarga de mostrar las pruebas unitarias del
 * moderador, como lo es agregar, eliminar, actualizar y leer
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModeradorTest {

    @Autowired
    private ModeradorRepo moderadorRepo;
    /**
     * Test encargado de comprobar el registro de un Moderador
     * con los datos necesarios como la cédula, nombre, correo, contraseña
     * y nickname del mismo para proceder a guardarlo al repositorio correspondiente
     */

    @Test
    public void registrarModeradorTest(){

        Moderador modeNuevo = new Moderador("1111", "Diego Valencia", "diego@hotmail.com",
                "admin", "diego");

        Moderador modeGuardado = moderadorRepo.save(modeNuevo);

        Assertions.assertNotNull(modeGuardado);
    }
    /**
     * Test encargado de comprobar la eliminación de un moderador
     * mediante la busqueda del mismo por el número de cédula
     */

    @Test
    public void eliminarModeradorTest(){

        Moderador modeNuevo = new Moderador("1111", "Diego Valencia", "diego@hotmail.com",
                "admin", "diego");

        moderadorRepo.save(modeNuevo);
        moderadorRepo.delete(modeNuevo);

        Moderador modeBorrado = moderadorRepo.findById("1111").orElse(null);
        Assertions.assertNull(modeBorrado);
    }
    /**
     * Test encargado de comprobar la actualización de datos de un moderador
     * en este caso la actualización del correo electrónico, buscando el
     * moderador mediante la cédula
     */

    @Test
    public void modificarModeradorTest(){

        Moderador modeNuevo = new Moderador("1111", "Diego Valencia", "diego@hotmail.com",
                "admin", "diego");

        Moderador modeGuardado = moderadorRepo.save(modeNuevo);
        modeGuardado.setEmail("diieegoo@gmail.com");
        moderadorRepo.save(modeGuardado);

        Moderador modeBuscado = moderadorRepo.findById("1111").orElse(null);
        Assertions.assertEquals("diieegoo@gmail.com", modeBuscado.getEmail());
    }
    /**
     * Test encargado de mostrar que los moderadores que están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:moderadores.sql")
    public void listarModeradores(){

        /*
        Moderador modeNuevo1 = new Moderador("4444", "Diego Valencia", "diego1@hotmail.com",
                "admin", "diego1");
        moderadorRepo.save(modeNuevo1);

        Moderador modeNuevo2 = new Moderador("5555", "Diego Valencia", "diego2@hotmail.com",
                "admin", "diego2");
        moderadorRepo.save(modeNuevo2);

        Moderador modeNuevo3 = new Moderador("6666", "Diego Valencia", "diego3@hotmail.com",
                "admin", "diego3");
        moderadorRepo.save(modeNuevo3);
        */

        List<Moderador> lista = moderadorRepo.findAll();
        System.out.println(lista);
    }
}
