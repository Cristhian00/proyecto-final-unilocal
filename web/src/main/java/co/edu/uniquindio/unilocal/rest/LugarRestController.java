package co.edu.uniquindio.unilocal.rest;

import co.edu.uniquindio.unilocal.dto.Mensaje;
import co.edu.uniquindio.unilocal.entidades.EstadoAprobacion;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lugar")
public class LugarRestController {

    @Autowired
    private LugarServicio lugarServicio;

    @GetMapping
    public List<Lugar> listar() {
        return lugarServicio.listarLugar();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable(name = "id") Integer id) {
        try {
            return ResponseEntity.status(201).body(lugarServicio.obtenerLugar(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity<Mensaje> registrar(@RequestBody Lugar lugar) {
        try {
            lugarServicio.registrarLugar(lugar);
            return ResponseEntity.status(201).body(new Mensaje("El lugar se creo correctamente"));
        } catch (Exception e) {

            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> actualizar(@RequestBody Lugar lugar) {

        try {
            lugarServicio.modificarLugar(lugar);
            return ResponseEntity.status(200).body(new Mensaje("El lugar se actualizo correctamente"));
        } catch (Exception e) {

            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name = "nombre") String nombre) {

        try {
            lugarServicio.eliminarLugar(nombre);
            return ResponseEntity.status(200).body(new Mensaje("El lugar se elimino correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> BuscarPorNombre(@PathVariable(name = "nombre") String nombre) {
        try {
            return ResponseEntity.status(200).body(lugarServicio.buscarLugaresPorPalabra(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerPorEstado (@PathVariable(name="estado") String estado){

        EstadoAprobacion esta = EstadoAprobacion.PENDIENTE;
        if (estado.equals("APROBADO")) {
            esta = EstadoAprobacion.APROBADO;
        } else if(estado.equals("RECHAZADO")){
            esta = EstadoAprobacion.RECHAZADO;
        }
        try {
            return  ResponseEntity.status(200).body(lugarServicio.listarLugaresEstado(esta));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @GetMapping("/idTipo/{idTipo}")
    public ResponseEntity<?> obtenerPorTipo (@PathVariable(name="idTipo") int idTipo){
        try {
            return  ResponseEntity.status(200).body(lugarServicio.listarLugaresTipo(idTipo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }


}
