package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Administrador;
import co.edu.uniquindio.unilocal.repositorios.AdministradorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImp implements AdministradorServicio {

    private final AdministradorRepo administradorRepo;

    public AdministradorServicioImp(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    public boolean emailDisponible(String email) {
        Optional<Administrador> usu = administradorRepo.findByEmail(email);
        return usu.isEmpty();
    }

    public boolean cedulaDisponible(String cedula) {
        Optional<Administrador> usu = administradorRepo.findByCedula(cedula);
        return usu.isEmpty();
    }

    public boolean nicknameDisponible(String nickname) {
        Optional<Administrador> usu = administradorRepo.findByNickname(nickname);
        return usu.isEmpty();
    }

    @Override
    public Administrador registraAdministrador(Administrador a) throws Exception {

        //Validaciones de la cédula
        if (a.getCedula() == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (!cedulaDisponible(a.getCedula())) {
            throw new Exception("La cédula ya se encuentra regitrada en otro administrador");
        }
        if (a.getCedula().length() > 12) {
            throw new Exception("El número de la cédula no puede contener más de 12 caracteres");
        }
        //Validaciones del nickname
        if (a.getNickname() == null) {
            throw new Exception("Debe ingresar un nickname");
        }
        if (!nicknameDisponible(a.getNickname())) {
            throw new Exception("El administrador con ese nickname ya existe");
        }
        if (a.getNickname().length() > 50) {
            throw new Exception("El nickname puede tener un máximo de 50 caracteres");
        }
        //Validaciones del correo electronico
        if (a.getEmail() == null) {
            throw new Exception("Debe ingresar un correo electronico");
        }
        if (!emailDisponible(a.getEmail())) {
            throw new Exception("El email ya se encuentra registrado en otro administrador");
        }
        if (a.getEmail().length() > 60) {
            throw new Exception("El email puede tener un máximo de 60 caracteres");
        }
        //Otras validaciones
        if (a.getContrasenia() == null) {
            throw new Exception("Debe ingresar una contraseña");
        }
        if (a.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre y apellido");
        }
        if (a.getNombre().length() > 100) {
            throw new Exception("El nombre debe tener un máximo de 100 caracteres");
        }

        return administradorRepo.save(a);
    }

    @Override
    public Administrador actualizarAdministrador(Administrador a) throws Exception {

        if (a.getCedula() == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (cedulaDisponible(a.getCedula())) {
            throw new Exception("La cédula no se encuentra registrada en ningún administrador");
        }
        if (a.getNickname() == null) {
            throw new Exception("Debe ingresar un nickname");
        }
        if (!obtenerAdministrador(a.getCedula()).getNickname().equals(a.getNickname())) {
            if (!nicknameDisponible(a.getNickname())) {
                throw new Exception("El nickname ya se enceuntra registrado en otro administrador");
            }
        }
        if (a.getNickname().length() > 50) {
            throw new Exception("El nickname puede tener un máximo de 50 caracteres");
        }
        if (a.getEmail() == null) {
            throw new Exception("Debe ingresar un correo electronico");
        }
        if (!obtenerAdministrador(a.getCedula()).getEmail().equals(a.getEmail())) {
            if (!emailDisponible(a.getEmail())) {
                throw new Exception("El email ya se encuentra registrado en otro administrador");
            }
            if (a.getEmail().length() > 60) {
                throw new Exception("El email puede tener un máximo de 60 caracteres");
            }
        }
        if (a.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre y apellido");
        }
        if (a.getNombre().length() > 100) {
            throw new Exception("El nombre debe tener un máximo de 100 caracteres");
        }
        Administrador adminAct = administradorRepo.save(a);
        return adminAct;
    }

    @Override
    public Administrador obtenerAdministrador(String cedula) throws Exception {

        if (cedula == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (cedulaDisponible(cedula)) {
            throw new Exception("El número de cédula no se encuentra registrada en ningún administrador");
        }
        return administradorRepo.obtenerAdministradorCedula(cedula);
    }

    @Override
    public boolean eliminarAdministrador(String cedula) throws Exception {

        if (cedula == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (cedulaDisponible(cedula)) {
            throw new Exception("El número de cédula no se encuentra registrada en ningún administrador");
        }
        Administrador usu = administradorRepo.obtenerAdministradorCedula(cedula);
        administradorRepo.delete(usu);

        return true;
    }

    @Override
    public List<Administrador> listaAdministradores() {
        return administradorRepo.obtenerAdministradores();
    }
}
