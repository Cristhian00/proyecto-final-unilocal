package unilocal.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.*;
import unilocal.repositorios.*;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InserPrincipalTest {

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
    private AdministradorRepo administradorRepo;
    @Autowired
    private ComentarioRepo comentarioRepo;
    @Autowired
    private ModeradorRepo moderadorRepo;

    @Test
    @Sql("classpath:pruebaPrincipal.sql")
    public void crearDatos(){

        List<Administrador> administradors = administradorRepo.findAll();
        for (Administrador a: administradors){
            System.out.println(a);
        }

        List<Ciudad> ciudads = ciudadRepo.findAll();
        for (Ciudad c: ciudads){
            System.out.println(c);
        }

        List<Comentario> comentarios = comentarioRepo.findAll();
        for (Comentario c: comentarios){
            System.out.println(c);
        }

        List<Departamento> departamentos = departamentoRepo.findAll();
        for (Departamento d: departamentos){
            System.out.println(d);
        }

        List<Horario> horarios = horarioRepo.findAll();
        for (Horario h: horarios){
            System.out.println(h);
        }

        List<Lugar> lugars = lugarRepo.findAll();
        for(Lugar l: lugars){
            System.out.println(l);
        }

        List<Moderador> moderadors = moderadorRepo.findAll();
        for (Moderador m: moderadors){
            System.out.println(m);
        }

        List<Usuario> usuarios = usuarioRepo.findAll();
        for (Usuario u: usuarios){
            System.out.println(u);
        }
    }
}
