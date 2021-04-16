package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.Administrador;
import unilocal.repositorios.AdministradorRepo;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    public void registrarAdministradorTest(){

        Administrador admiNuevo = new Administrador("2222", "Tatiana Arboleda", "tatiana@hotmail.com",
                "admin", "tata");

        Administrador admiGuardado = administradorRepo.save(admiNuevo);

        Assertions.assertNotNull(admiGuardado);
    }

    @Test
    public void eliminarAdministradorTest(){

        Administrador admiNuevo = new Administrador("2222", "Tatiana Arboleda", "tatiana@hotmail.com",
                "admin", "tata");

        administradorRepo.save(admiNuevo);
        administradorRepo.delete(admiNuevo);

        Administrador admiBorrado = administradorRepo.findById("2222").orElse(null);
        Assertions.assertNull(admiBorrado);
    }

    @Test
    public void actualizarAdministradorTest(){

        Administrador admiNuevo = new Administrador("2222", "Tatiana Arboleda", "tatiana@hotmail.com",
                "admin", "tata");

        Administrador admiGuardado = administradorRepo.save(admiNuevo);
        admiGuardado.setEmail("tata@gmail.com");
        administradorRepo.save(admiGuardado);

        Administrador admiBuscado = administradorRepo.findById("2222").orElse(null);
        Assertions.assertEquals("tata@gmail.com", admiBuscado.getEmail());
    }

    @Test
    @Sql("classpath:administradores.sql")
    public void listarAdministradores(){

        /*
        Administrador admiNuevo1 = new Administrador("7777", "Tatiana Arboleda", "tatiana1@hotmail.com",
                "admin", "tata1");
        administradorRepo.save(admiNuevo1);

        Administrador admiNuevo2 = new Administrador("8888", "Tatiana Arboleda", "tatiana2@hotmail.com",
                "admin", "tata2");
        administradorRepo.save(admiNuevo2);

        Administrador admiNuevo3 = new Administrador("9999", "Tatiana Arboleda", "tatiana3@hotmail.com",
                "admin", "tata3");
        administradorRepo.save(admiNuevo3);
         */

        List<Administrador> lista = administradorRepo.findAll();
        System.out.println(lista);
    }
}
