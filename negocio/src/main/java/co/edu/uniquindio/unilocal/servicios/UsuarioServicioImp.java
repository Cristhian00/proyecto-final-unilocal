package co.edu.uniquindio.unilocal.servicios;

import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImp(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean emailDisponible(String email) {
        Optional<Usuario> usu = usuarioRepo.findByEmail(email);
        return usu.isEmpty();
    }

    public boolean cedulaDisponible(String cedula) {
        Optional<Usuario> usu = usuarioRepo.findByCedula(cedula);
        return usu.isEmpty();
    }

    public boolean nicknameDisponible(String nickname) {
        Optional<Usuario> usu = usuarioRepo.findByNickname(nickname);
        return usu.isEmpty();
    }

    @Override
    public Usuario registraUsuario(Usuario u) throws Exception {

        //Validaciones de la cédula
        if (u.getCedula() == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (!cedulaDisponible(u.getCedula())) {
            throw new Exception("La cédula ya se encuentra regitrada en otro usuario");
        }
        if (u.getCedula().length() > 12) {
            throw new Exception("El número de la cédula no puede contener más de 12 caracteres");
        }
        //Validaciones del nickname
        if (u.getNickname() == null) {
            throw new Exception("Debe ingresar un nickname");
        }
        if (!nicknameDisponible(u.getNickname())) {
            throw new Exception("El usuario con ese nickname ya existe");
        }
        if (u.getNickname().length() > 50) {
            throw new Exception("El nickname puede tener un máximo de 50 caracteres");
        }
        //Validaciones del correo electronico
        if (u.getEmail() == null) {
            throw new Exception("Debe ingresar un correo electronico");
        }
        if (!emailDisponible(u.getEmail())) {
            throw new Exception("El email ya se encuentra registrado en otro usuario");
        }
        if (u.getEmail().length() > 60) {
            throw new Exception("El email puede tener un máximo de 60 caracteres");
        }
        //Otras validaciones
        if (u.getContrasenia() == null) {
            throw new Exception("Debe ingresar una contraseña");
        }
        if (u.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre y apellido");
        }
        if (u.getNombre().length() > 100) {
            throw new Exception("El nombre debe tener un máximo de 100 caracteres");
        }

        Usuario usuarioNew = usuarioRepo.save(u);
        return usuarioNew;
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        if (u.getCedula() == null) {
            throw new Exception("Debe ingresar un número de cédula");
        }
        if (cedulaDisponible(u.getCedula())) {
            throw new Exception("La cédula no se encuentra registrada a ningún usuario");
        }
        if (u.getNickname() == null) {
            throw new Exception("Debe ingresar un nickname");
        }
        if (!obtenerUsuario(u.getCedula()).getNickname().equals(u.getNickname())) {
            if (!nicknameDisponible(u.getNickname())) {
                throw new Exception("El nickname ya se enceuntra registrado en otro usuario");
            }
        }
        if (u.getNickname().length() > 50) {
            throw new Exception("El nickname puede tener un máximo de 50 caracteres");
        }
        if (u.getEmail() == null) {
            throw new Exception("Debe ingresar un correo electronico");
        }
        if (!obtenerUsuario(u.getCedula()).getEmail().equals(u.getEmail())) {
            if (!emailDisponible(u.getEmail())) {
                throw new Exception("El email ya se encuentra registrado en otro usuario");
            }
        }
        if (u.getEmail().length() > 60) {
            throw new Exception("El email puede tener un máximo de 60 caracteres");
        }
        if (u.getNombre() == null) {
            throw new Exception("Debe ingresar un nombre y apellido");
        }
        if (u.getNombre().length() > 100) {
            throw new Exception("El nombre debe tener un máximo de 100 caracteres");
        }
        Usuario usuAct = usuarioRepo.save(u);
        return usuAct;
    }

    @Override
    public Usuario obtenerUsuario(String cedula) throws Exception {

        if(cedula == null){
            throw new Exception("Debe ingresar un número de cédula");
        }
        if(cedulaDisponible(cedula)){
            throw new Exception("El número de cédula no se encuentra registrada en ningún usuario");
        }
        return usuarioRepo.obtenerUsuarioCedula(cedula);
    }

    @Override
    public boolean eliminarUsuario(String cedula) throws Exception {

        if(cedula == null){
            throw new Exception("Debe ingresar un número de cédula");
        }
        if(cedulaDisponible(cedula)){
            throw new Exception("El número de cédula no se encuentra registrada en ningún usuario");
        }
        Usuario usu = usuarioRepo.obtenerUsuarioCedula(cedula);
        usuarioRepo.delete(usu);

        return true;
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioRepo.obtenerUsuarios();
    }
}
