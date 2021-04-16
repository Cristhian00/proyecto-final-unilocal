package unilocal.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import unilocal.entidades.Comentario;
import unilocal.repositorios.ComentarioRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    public void registrarComentarioTest(){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");

            Comentario comenNuevo = new Comentario("Un excelente hotel",4.5,fecha);
            Comentario comenGuardado = comentarioRepo.save(comenNuevo);
            Assertions.assertNotNull(comenGuardado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarComentarioTest(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");

            Comentario comenNuevo = new Comentario("Un excelente hotel",4.5,fecha);
            comentarioRepo.save(comenNuevo);
            comentarioRepo.delete(comenNuevo);

            Comentario comenBorrado = comentarioRepo.findById(1).orElse(null);

            Assertions.assertNull(comenBorrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modificarComentarioTest(){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");

            Comentario comenNuevo = new Comentario("Un excelente hotel",4.5,fecha);
            Comentario comenGuardado = comentarioRepo.save(comenNuevo);
            comenGuardado.setComentario("Pesimo servicio");
            comentarioRepo.save(comenGuardado);

            Comentario comenBuscado = comentarioRepo.findById(1).orElse(null);

            Assertions.assertEquals("Pesimo servicio",comenBuscado.getComentario());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void listarComentariosTest(){
        /*
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");

            Comentario comenNuevo1 = new Comentario("Un excelente hotel",4.5,fecha);
            Comentario comenNuevo2 = new Comentario("Mal servicio",1,fecha);
            Comentario comenNuevo3 = new Comentario("Que buena comida",5,fecha);

            comentarioRepo.save(comenNuevo1);
            comentarioRepo.save(comenNuevo2);
            comentarioRepo.save(comenNuevo3);

        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        List<Comentario> lista = comentarioRepo.findAll();
        System.out.println(lista);
    }
}
