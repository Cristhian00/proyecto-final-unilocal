package co.edu.uniquindio.unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.repositorios.CiudadRepo;
import co.edu.uniquindio.unilocal.repositorios.DepartamentoRepo;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;

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
    public void registrarUsuarioTest() {

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio", "Colombia");
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
    public void eliminarUsuarioTest() {

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuNuevo.setTelefono(telefonos);
        usuarioRepo.save(usuNuevo);
        usuarioRepo.delete(usuNuevo);

        Usuario usuBorrado = usuarioRepo.findById("111").orElse(null);

        Assertions.assertNull(usuBorrado);
    }

    /**
     * Test encargado de comprobar la actualización de datos de un usuario
     * en este caso la actualización del correo electrónico, buscando el
     * usuario mediante la cédula
     */
    @Test
    public void modificarUsuarioTest() {

        Map<String, String> telefonos = new HashMap<String, String>();
        telefonos.put("3136778765", "Celular");
        telefonos.put("7536969", "telefono");

        Departamento departamento = new Departamento("Quindio", "Colombia");
        departamentoRepo.save(departamento);
        Ciudad ciudadNueva = new Ciudad("Armenia", departamento);
        ciudadRepo.save(ciudadNueva);

        Usuario usuNuevo = new Usuario("111", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris", ciudadNueva);
        usuNuevo.setTelefono(telefonos);
        Usuario usuGuardado = usuarioRepo.save(usuNuevo);
        usuGuardado.setEmail("cristhian@gmail.com");
        usuarioRepo.save(usuGuardado);

        Usuario usuBuscado = usuarioRepo.findById("111").orElse(null);

        Assertions.assertEquals("cristhian@gmail.com", usuBuscado.getEmail());
    }

    /**
     * Test encargado de mostrar los usuarios que están registrados,
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosTest() {

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
        for (Usuario u : lista) {
            System.out.println(u);
        }
    }

    /**
     * Test que busca un usuario por nombre en la lista de usuarios con
     * un método creado en UsuarioRepo
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPorNombreTest() {

        List<Usuario> lista = usuarioRepo.findByNombre("Pedro Lopez");

        Assertions.assertNotNull(lista);
    }

    /**
     * Test que imprime la lista de usuarios por paginas
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPaginablesTest() {

        List<Usuario> lista = usuarioRepo.obtenerUsuarios(PageRequest.of(1, 2));
        for (Usuario u : lista) {
            System.out.println(u);
        }
    }

    /**
     * Test que imprime la lista de usuarios ordenados por algún atributo
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosOrdenadosTest() {

        List<Usuario> lista = usuarioRepo.obtenerUsuarios(Sort.by("nombre"));
        for (Usuario u : lista) {
            System.out.println(u);
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarLugaresFavoritosTest() {

        List<Lugar> lugares = usuarioRepo.obtenerLugaresFavoritos("111");

        for (Lugar l : lugares) {
            System.out.println(l.getNombre());
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarLugaresCreadosTest() {

        List<Object[]> lugares = usuarioRepo.obtenerLugaresPublicadosEEmail();

        for (Object[] l : lugares) {
            System.out.println(l[0] + " - " + l[1]);
        }
    }
}
