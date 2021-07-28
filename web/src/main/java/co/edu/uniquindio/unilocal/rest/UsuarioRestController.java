package co.edu.uniquindio.unilocal.rest;

import co.edu.uniquindio.unilocal.dto.Mensaje;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listar() {

        return usuarioServicio.listaUsuarios();
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Usuario usuario) {
        try{
            usuarioServicio.registraUsuario(usuario);
            return ResponseEntity.status(201).body((new Mensaje("El usuario se a registrado correctamente")));
        }catch (Exception e){
            return ResponseEntity.status(500).body((new Mensaje(e.getMessage())));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Usuario usuario) {
        try{
            usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(200).body((new Mensaje("El usuario se a actualizado correctamente")));
        }catch (Exception e){
            return ResponseEntity.status(500).body((new Mensaje(e.getMessage())));
        }
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name = "cedula")String cedula) {
        try{
            usuarioServicio.eliminarUsuario(cedula);
            return ResponseEntity.status(200).body((new Mensaje("El usuario se a eliminado correctamente")));
        }catch (Exception e){
            return ResponseEntity.status(500).body((new Mensaje(e.getMessage())));
        }
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> obtener(@PathVariable(name = "cedula") String cedula) {
        try{
            return ResponseEntity.status(200).body(usuarioServicio.obtenerUsuario(cedula));
        }catch (Exception e){
            return ResponseEntity.status(500).body((new Mensaje(e.getMessage())));
        }
    }
}
