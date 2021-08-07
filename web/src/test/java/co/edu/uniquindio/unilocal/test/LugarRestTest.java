package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.WebApplication;
import co.edu.uniquindio.unilocal.entidades.EstadoAprobacion;
import co.edu.uniquindio.unilocal.entidades.Lugar;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
import co.edu.uniquindio.unilocal.servicios.LugarServicio;
import co.edu.uniquindio.unilocal.servicios.TipoLugarServicio;
import co.edu.uniquindio.unilocal.servicios.UsuarioServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc
public class LugarRestTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TipoLugarServicio tipoLugarServicio;
    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Test
    @Transactional
    public void guardarUsuarioTest() throws Exception {


        Lugar l = new Lugar("Juan B", "Restaurante de truchas", tipoLugarServicio.obtenerTipoLugar(2), ciudadServicio.obtenerCiudad(3),
                new Date(), 4.55092F, -75.6557F, EstadoAprobacion.PENDIENTE, usuarioServicio.obtenerUsuario("1030677"));
        mockMvc.perform(post("/api/lugar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(l)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    public void actualizarLugarTest() throws Exception {
        Lugar l = new Lugar("Juan B", "Restaurante de truchas", tipoLugarServicio.obtenerTipoLugar(2), ciudadServicio.obtenerCiudad(3),
                new Date(), 43.55092F, -76.6557F, EstadoAprobacion.PENDIENTE, usuarioServicio.obtenerUsuario("1030677"));
        l.setId(9);
        l.setNombre("Suanfonsoft");
        mockMvc.perform(put("/api/lugar/{id}", l.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(l)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void eliminarUsarioTest() throws Exception {
        mockMvc.perform(delete("/api/lugar/{nombre}", "Alejandro Murillo")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obtenerListaUsuariosTest() throws Exception {
        mockMvc.perform(get("/api/lugar/")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obtenerUsuarioTest() throws Exception {
        mockMvc.perform(get("/api/lugar/{id}", 1030677)
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void obtenerPorEstado() throws Exception {
        mockMvc.perform(get("/api/lugar/estado/{estado}", EstadoAprobacion.APROBADO)
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obtenerPorTipo() throws Exception {
        mockMvc.perform(get("/api/lugar/{idTipo}", 2)
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }
}
