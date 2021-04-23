package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.Ciudad;
import unilocal.entidades.Departamento;
import unilocal.entidades.Usuario;
import unilocal.repositorios.CiudadRepo;
import unilocal.repositorios.DepartamentoRepo;
import unilocal.repositorios.UsuarioRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private DepartamentoRepo departamentoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    /**
     * Test encargado de comprobar el registro de un usuario
     * con los datos necesarios como la cédula, nombre, correo, contraseña
     * y nickname del usuario para proceder a guardarlo al repositorio correspondiente
     */
    @Test
    public void registrarUsuarioTest(){

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio","Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuNuevo.setTelefono(telefonos);
        Usuario usuGuardado = usuarioRepo.save(usuNuevo);

        Assertions.assertNotNull(usuGuardado);
    }

    /**
     * Test encargado de comprobar la eliminación de un usuario
     * mediante la busqueda del mismo por el número de cédula
     */
    @Test
    public void eliminarUsuarioTest(){

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio","Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris",ciudadNueva);
        usuNuevo.setTelefono(telefonos);
        usuarioRepo.save(usuNuevo);
        usuarioRepo.delete(usuNuevo);

        Usuario usuBorrado =  usuarioRepo.findById("111").orElse(null);

        Assertions.assertNull(usuBorrado);
    }

    /**
     * Test encargado de comprobar la actualización de datos de un usuario
     * en este caso la actualización del correo electrónico, buscando el
     * usuario mediante la cédula
     */
    @Test
    public void modificarUsuarioTest(){

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio","Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuNuevo.setTelefono(telefonos);
        Usuario usuGuardado = usuarioRepo.save(usuNuevo);
        usuGuardado.setEmail("cristhian@gmail.com");
        usuarioRepo.save(usuGuardado);

        Usuario usuBuscado =  usuarioRepo.findById("111").orElse(null);

        Assertions.assertEquals("cristhian@gmail.com", usuBuscado.getEmail());
    }

    /**
     * Test encargado de mostrar que los usuarios que están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuarios(){

        /*
        Usuario usuNuevo1 = new Usuario("111", "Cristhian Ortiz", "cristhian1@hotmail.com",
                "admin", "cris1");
        usuarioRepo.save(usuNuevo1);

        Usuario usuNuevo2 = new Usuario("112", "Cristhian Ortiz", "cristhian2@hotmail.com",
                "admin", "cris2");
        usuarioRepo.save(usuNuevo2);

        Usuario usuNuevo3 = new Usuario("113", "Cristhian Ortiz", "cristhian3@hotmail.com",
                "admin", "cris3");
        usuarioRepo.save(usuNuevo3);
        */

        List<Usuario> lista = usuarioRepo.findAll();
        for (Usuario u: lista){
            System.out.println(u);
        }
    }
}
