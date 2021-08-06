package co.edu.uniquindio.unilocal.rest;

import co.edu.uniquindio.unilocal.dto.Mensaje;
import co.edu.uniquindio.unilocal.entidades.Ciudad;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ciudad")
public class CiudadRestController {

    private CiudadServicio ciudadServicio;

    @GetMapping
    public List<Ciudad> listar (){
        return ciudadServicio.listarCiudades();
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear (@RequestBody Ciudad ciudad){
        try {
            ciudadServicio.registrarCiudad(ciudad);
            return  ResponseEntity.status(201).body(new Mensaje("La ciudad se creo correctamente"));
        } catch (Exception e) {

            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name="id") Integer  id){

        try {
            ciudadServicio.eliminarCiudad(id);
            return  ResponseEntity.status(201).body(new Mensaje("La ciudad  se elimino correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener (@PathVariable(name="id") Integer id){
        try {
            ;
            return  ResponseEntity.status(201).body(ciudadServicio.obtenerCiudad(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

}
