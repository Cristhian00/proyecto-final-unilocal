package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.entidades.Moderador;
import co.edu.uniquindio.unilocal.repositorios.ModeradorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeradorServicioImp implements ModeradorServicio{

    private final ModeradorRepo moderadorRepo;

    public ModeradorServicioImp(ModeradorRepo moderadorRepo) {
        this.moderadorRepo = moderadorRepo;
    }

    public boolean emailDisponible(String email) {
        Optional<Moderador> usu = moderadorRepo.findByEmail(email);
        return usu.isEmpty();
    }

    public boolean cedulaDisponible(String cedula) {
        Optional<Moderador> usu = moderadorRepo.findByCedula(cedula);
        return usu.isEmpty();
    }

    public boolean nicknameDisponible(String nickname) {
        Optional<Moderador> usu = moderadorRepo.findByNickname(nickname);
        return usu.isEmpty();
    }

    @Override
    public Moderador registraModerador(Moderador m) throws Exception {
        //Validaciones de la cédula
        if (m.getCedula() == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (!cedulaDisponible(m.getCedula())) {
            throw new Exception("La cédula ya se encuentra regitrada en otro moderador");
        }
        if (m.getCedula().length() > 12) {
            throw new Exception("El número de la cédula no puede contener más de 12 caracteres");
        }
        //Validaciones del nickname
        if (m.getNickname() == null) {
            throw new Exception("Debe ingresar un nickname");
        }
        if (!nicknameDisponible(m.getNickname())) {
            throw new Exception("El usuario con ese nickname ya existe");
        }
        if (m.getNickname().length() > 50) {
            throw new Exception("El nickname puede tener un máximo de 50 caracteres");
        }
        //Validaciones del correo electronico
        if (m.getEmail() == null) {
            throw new Exception("Debe ingresar un correo electronico");
        }
        if (!emailDisponible(m.getEmail())) {
            throw new Exception("El email ya se encuentra registrado en otro moderador");
        }
        if (m.getEmail().length() > 60) {
            throw new Exception("El email puede tener un máximo de 60 caracteres");
        }
        //Otras validaciones
        if (m.getContrasenia() == null) {
            throw new Exception("Debe ingresar una contraseña");
        }
        if (m.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre y apellido");
        }
        if (m.getNombre().length() > 100) {
            throw new Exception("El nombre debe tener un máximo de 100 caracteres");
        }

        Moderador modNew = moderadorRepo.save(m);
        return modNew;
    }

    @Override
    public Moderador actualizarModerador(Moderador m) throws Exception {

        if (m.getCedula() == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (cedulaDisponible(m.getCedula())) {
            throw new Exception("La cédula no se encuentra registrada a ningún moderador");
        }
        if (m.getNickname() == null) {
            throw new Exception("Debe ingresar un nickname");
        }
        if (!obtenerModerador(m.getCedula()).getNickname().equals(m.getNickname())) {
            if (!nicknameDisponible(m.getNickname())) {
                throw new Exception("El nickname ya se enceuntra registrado en otro moderador");
            }
        }
        if (m.getNickname().length() > 50) {
            throw new Exception("El nickname puede tener un máximo de 50 caracteres");
        }
        if (m.getEmail() == null) {
            throw new Exception("Debe ingresar un correo electronico");
        }
        if (!obtenerModerador(m.getCedula()).getEmail().equals(m.getEmail())) {
            if (!emailDisponible(m.getEmail())) {
                throw new Exception("El email ya se encuentra registrado en otro moderador");
            }
        }
        if (m.getEmail().length() > 60) {
            throw new Exception("El email puede tener un máximo de 60 caracteres");
        }
        if (m.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre y apellido");
        }
        if (m.getNombre().length() > 100) {
            throw new Exception("El nombre debe tener un máximo de 100 caracteres");
        }
        Moderador modAct = moderadorRepo.save(m);
        return modAct;
    }

    @Override
    public Moderador obtenerModerador(String cedula) throws Exception {

        if(cedula == null){
            throw new Exception("Debe ingresar un número de cédula");
        }
        if(cedulaDisponible(cedula)){
            throw new Exception("El número de cédula no se encuentra registrada en ningún moderador");
        }
        return moderadorRepo.obtenerModeradorCedula(cedula);
    }

    @Override
    public boolean eliminarModerador(String cedula) throws Exception {

        if(cedula == null){
            throw new Exception("Debe ingresar un número de cédula");
        }
        if(cedulaDisponible(cedula)){
            throw new Exception("El número de cédula no se encuentra registrada en ningún moderador");
        }
        Moderador mod = moderadorRepo.obtenerModeradorCedula(cedula);
        moderadorRepo.delete(mod);

        return true;
    }

    @Override
    public List<Moderador> listaModerador() {
        return moderadorRepo.obtenerModeradores();
    }

    @Override
    public List<Lugar> obtenerLugaresRevisados(String cedula) {
        return moderadorRepo.obtenerLugaresRevisados(cedula);
    }

    @Override
    public List<Lugar> obtenerLugaresAprobados(String cedula) {
        return moderadorRepo.obtenerLugaresAprobados(cedula);
    }

    @Override
    public List<Lugar> obtenerLugaresRechazados(String cedula) {
        return moderadorRepo.obtenerLugaresRechazados(cedula);
    }
}
