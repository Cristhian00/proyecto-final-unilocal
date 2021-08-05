package co.edu.uniquindio.unilocal.config;

import co.edu.uniquindio.unilocal.entidades.*;
import co.edu.uniquindio.unilocal.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class informacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio adminServicio;

    @Autowired
    private ModeradorServicio modeServicio;

    @Autowired
    private UsuarioServicio usuServicio;

    @Autowired
    private DepartamentoServicio deparServicio;

    @Autowired
    private CiudadServicio ciuServicio;

    @Autowired
    private LugarServicio lugServicio;

    @Autowired
    private HorarioServicio horServicio;

    @Autowired
    private TipoLugarServicio tipoServicio;

    @Autowired
    private ComentarioServicio comenservicio;

    @Override
    public void run(String... args) throws Exception {


        if (adminServicio.listaAdministradores().isEmpty()) {
            Administrador admin1 = new Administrador("1030677817", "Cristhian Ortiz",
                    "cortizm_1@uqvirtual.edu.co", "admin123", "admin1");
            adminServicio.registraAdministrador(admin1);

            Administrador admin2 = new Administrador("1030677818", "Diego Valencia",
                    "dmvalenciah_1@uqvirtual.edu.co", "admin123", "admin2");
            adminServicio.registraAdministrador(admin2);

        }

        if (modeServicio.listaModerador().isEmpty()) {
            Administrador adm1 = adminServicio.obtenerAdministrador("1030677817");
            Administrador adm2 = adminServicio.obtenerAdministrador("1030677818");

            Moderador mode1 = new Moderador("1030677819", "Tatiana Arboleda",
                    "tatiArbo@uqvirtual.edu.co", "mode123", "mode1", adm1);
            modeServicio.registraModerador(mode1);

            Moderador mode2 = new Moderador("1030677820", "Mariam Bolivar",
                    "mariamBo@uqvirtual.edu.co", "mode123", "mode2", adm2);
            modeServicio.registraModerador(mode2);

            Moderador mode3 = new Moderador("1030677821", "Yulie Andrea Martinez",
                    "YulieMartinez@uqvirtual.edu.co", "mode123", "mode3", adm1);
            modeServicio.registraModerador(mode3);
        }

        if (tipoServicio.listarTipoLugar().isEmpty()) {
            TipoLugar tip1 = new TipoLugar("Restaurante");
            TipoLugar tip2 = new TipoLugar("Cáfe");
            TipoLugar tip3 = new TipoLugar("Pizzeria");
            TipoLugar tip4 = new TipoLugar("Comida Rápida");
            TipoLugar tip5 = new TipoLugar("Bar");
            TipoLugar tip6 = new TipoLugar("Discoteca");
            TipoLugar tip7 = new TipoLugar("Heladeria");
            TipoLugar tip8 = new TipoLugar("Cáfe-Bar");

            tipoServicio.registrarTipoLugar(tip1);
            tipoServicio.registrarTipoLugar(tip2);
            tipoServicio.registrarTipoLugar(tip3);
            tipoServicio.registrarTipoLugar(tip4);
            tipoServicio.registrarTipoLugar(tip5);
            tipoServicio.registrarTipoLugar(tip6);
            tipoServicio.registrarTipoLugar(tip7);
            tipoServicio.registrarTipoLugar(tip8);
        }

        if (deparServicio.listarDepartamento().isEmpty()) {
            Departamento depar1 = new Departamento("Quindio", "Colombia");
            depar1.setId(1);
            deparServicio.registrarDepartamento(depar1);

            Departamento depar2 = new Departamento("Risaralda", "Colombia");
            depar2.setId(2);
            deparServicio.registrarDepartamento(depar2);

            Departamento depar3 = new Departamento("Caldas", "Colombia");
            depar3.setId(3);
            deparServicio.registrarDepartamento(depar3);
        }

        if (ciuServicio.listarCiudades().isEmpty()) {
            Departamento depar1 = deparServicio.obtenerDepartamento(1);
            Departamento depar2 = deparServicio.obtenerDepartamento(2);
            Departamento depar3 = deparServicio.obtenerDepartamento(3);

            Ciudad ciu1 = new Ciudad("Armenia", depar1);
            ciu1.setId(1);
            ciuServicio.registrarCiudad(ciu1);

            Ciudad ciu2 = new Ciudad("Pereira", depar2);
            ciu2.setId(2);
            ciuServicio.registrarCiudad(ciu2);

            Ciudad ciu3 = new Ciudad("Manizales", depar3);
            ciu3.setId(3);
            ciuServicio.registrarCiudad(ciu3);

            Ciudad ciu4 = new Ciudad("Salento", depar1);
            ciu4.setId(4);
            ciuServicio.registrarCiudad(ciu4);

            Ciudad ciu5 = new Ciudad("Dos Quebradas", depar2);
            ciu5.setId(5);
            ciuServicio.registrarCiudad(ciu5);
        }

        if (usuServicio.listaUsuarios().isEmpty()) {

            Ciudad ciu1 = ciuServicio.obtenerCiudad(1);
            Ciudad ciu2 = ciuServicio.obtenerCiudad(2);
            Ciudad ciu3 = ciuServicio.obtenerCiudad(3);

            Map<String, String> tels = new HashMap<>();
            tels.put("casa", "1234");
            tels.put("movil", "262626");

            Usuario usu1 = new Usuario("1030677", "Alejandro Murillo", "alejo@mail.com", "usu123", "usu1", ciu1);
            usu1.setTelefono(tels);
            usuServicio.registraUsuario(usu1);

            Usuario usu2 = new Usuario("1030678", "Andres Ortiz", "andres@mail.com", "usu123", "usu2", ciu2);
            usu2.setTelefono(tels);
            usuServicio.registraUsuario(usu2);

            Usuario usu3 = new Usuario("1030679", "Tobias Martinez", "tobias@mail.com", "usu123", "usu3", ciu3);
            usu3.setTelefono(tels);
            usuServicio.registraUsuario(usu3);
        }

        if (lugServicio.listarLugar().isEmpty()) {

            Usuario usu1 = usuServicio.obtenerUsuario("1030677");
            Usuario usu2 = usuServicio.obtenerUsuario("1030678");

            Ciudad ciu1 = ciuServicio.obtenerCiudad(1);
            Ciudad ciu2 = ciuServicio.obtenerCiudad(2);
            Ciudad ciu3 = ciuServicio.obtenerCiudad(3);

            TipoLugar tip1 = tipoServicio.obtenerTipoLugar(1);
            TipoLugar tip2 = tipoServicio.obtenerTipoLugar(2);
            TipoLugar tip3 = tipoServicio.obtenerTipoLugar(3);

            Moderador mod1 = modeServicio.obtenerModerador("1030677819");

            Lugar l1 = new Lugar("Juan B", "Restaurante de truchas", tip1, ciu1,
                    new Date(), 4.55092F, -75.6557F, EstadoAprobacion.PENDIENTE, usu1);
            l1.setImagenes(new ArrayList<>());
            l1.setHorarios(new ArrayList<>());
            l1.setTelefono(new HashMap<>());
            lugServicio.registrarLugar(l1);

            Horario hor1 = new Horario("Lunes a Jueves", "08:00", "18:00", l1);
            Horario hor2 = new Horario("Viernes y Sabado", "18:00", "03:00", l1);
            Horario hor3 = new Horario("Domingo", "18:00", "01:00", l1);
            horServicio.registrarHorario(hor1);
            horServicio.registrarHorario(hor2);
            horServicio.registrarHorario(hor3);

            Lugar l2 = new Lugar("Caffeto", "Cafe-bar con buena música", tip2, ciu2,
                    new Date(), 4.52892F, -75.6775F, EstadoAprobacion.APROBADO, usu2);
            l2.setModerador(mod1);
            l2.setImagenes(new ArrayList<>());
            l2.setHorarios(new ArrayList<>());
            l2.setTelefono(new HashMap<>());
            lugServicio.registrarLugar(l2);

            Lugar l3 = new Lugar("La conquista", "Restaurante de comidas rápidas", tip3, ciu3,
                    new Date(), 4.54029F, -75.6667F, EstadoAprobacion.PENDIENTE, usu1);
            l3.setImagenes(new ArrayList<>());
            l3.setHorarios(new ArrayList<>());
            l3.setTelefono(new HashMap<>());
            lugServicio.registrarLugar(l3);

        }

        if(horServicio.listarHorario().isEmpty()){

            Lugar l1 = lugServicio.obtenerLugar(4);

            Horario hor1 = new Horario("Lunes a Jueves", "08:00", "18:00", l1);
            Horario hor2 = new Horario("Viernes y Sabado", "18:00", "03:00", l1);
            Horario hor3 = new Horario("Domingo", "18:00", "01:00", l1);
            horServicio.registrarHorario(hor1);
            horServicio.registrarHorario(hor2);
            horServicio.registrarHorario(hor3);
        }

    }
}
