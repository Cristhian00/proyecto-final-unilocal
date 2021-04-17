package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.Usuario;
import unilocal.repositorios.UsuarioRepo;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarUsuarioTest(){

        Usuario usuNuevo = new Usuario("1234", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris");

        Usuario usuGuardado = usuarioRepo.save(usuNuevo);

        Assertions.assertNotNull(usuGuardado);
    }

    @Test
    public void eliminarUsuarioTest(){

        Usuario usuNuevo = new Usuario("1234", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris");

        usuarioRepo.save(usuNuevo);
        usuarioRepo.delete(usuNuevo);

        Usuario usuBorrado =  usuarioRepo.findById("1234").orElse(null);

        Assertions.assertNull(usuBorrado);
    }

    @Test
    public void modificarUsuarioTest(){

        Usuario usuNuevo = new Usuario("1234", "Cristhian Ortiz", "cristhian@hotmail.com",
                "admin", "cris");

        Usuario usuGuardado = usuarioRepo.save(usuNuevo);
        usuGuardado.setEmail("cristhian@gmail.com");
        usuarioRepo.save(usuGuardado);

        Usuario usuBuscado =  usuarioRepo.findById("1234").orElse(null);

        Assertions.assertEquals("cristhian@gmail.com", usuBuscado.getEmail());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuarios(){

        /*
        Usuario usuNuevo1 = new Usuario("1111", "Cristhian Ortiz", "cristhian1@hotmail.com",
                "admin", "cris1");
        usuarioRepo.save(usuNuevo1);

        Usuario usuNuevo2 = new Usuario("2222", "Cristhian Ortiz", "cristhian2@hotmail.com",
                "admin", "cris2");
        usuarioRepo.save(usuNuevo2);

        Usuario usuNuevo3 = new Usuario("3333", "Cristhian Ortiz", "cristhian3@hotmail.com",
                "admin", "cris3");
        usuarioRepo.save(usuNuevo3);
        */

        List<Usuario> lista = usuarioRepo.findAll();
        System.out.println(lista);
    }
}