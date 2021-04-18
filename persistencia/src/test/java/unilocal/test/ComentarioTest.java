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

/**
 * Test que se encarga de mostrar las pruebas unitarias del
 * comentario, como lo es agregar, eliminar, actualizar y leer
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;
    /**
     * Test encargado de comprobar el registro de un comentario
     * con los datos necesarios como la fecha y el mensaje
     * del comentario para proceder a guardarlo en el repositorio correspondiente
     */
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
    /**
     * Test encargado de comprobar la eliminación de un comentario
     * mediante la busqueda del mismo por el número de registro del comentario
     */
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
    /**
     * Test encargado de comprobar la actualización de datos de un comentario
     * en este caso la actualización del mensaje, buscando el
     * comentario mediante el numero de registro del comentario
     */
    @Test
    public void modificarComentarioTest(){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("2021/04/15");

            Comentario comenNuevo = new Comentario("Un excelente hotel",4.5,fecha);
            Comentario comenGuardado = comentarioRepo.save(comenNuevo);
            comenGuardado.setMensaje("Pesimo servicio");
            comentarioRepo.save(comenGuardado);

            Comentario comenBuscado = comentarioRepo.findById(1).orElse(null);

            Assertions.assertEquals("Pesimo servicio",comenBuscado.getMensaje());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Test encargado de mostrar que los comentarios están registrados
     * trayendo a todos los que están registrados en el repositorio y agregándolos
     * a una lista para luego imprimirla
     */
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
