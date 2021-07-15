package co.edu.uniquindio.unilocal;

import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.DepartamentoServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.entidades.Departamento;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private DepartamentoServicio departamentoServicio;

    @Test
    public void registrarUsuario(){

        try {

            Departamento depar = new Departamento("Quindio","Colombia");
            depar = departamentoServicio.registrarDepartamento(depar);

            Ciudad ciu = new Ciudad("Armenia", depar);
            ciu = ciudadServicio.registrarCiudad(ciu);

            Usuario u = new Usuario("123", "Pepito Perez", "pepito@mail.com",
                    "123", "pepito", ciu);
            Usuario uNew = usuarioServicio.registraUsuario(u);

            System.out.println(uNew);
            Assertions.assertNotNull(uNew);

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
