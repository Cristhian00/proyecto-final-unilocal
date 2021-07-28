package co.edu.uniquindio.unilocal.test;

import co.edu.uniquindio.unilocal.WebApplication;
import co.edu.uniquindio.unilocal.entidades.Usuario;
import co.edu.uniquindio.unilocal.servicios.CiudadServicio;
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

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc
public class UsuarioRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    @Transactional
    public void registrarTest() throws Exception {

        Map<String, String> tels = new HashMap<>();
        tels.put("casa", "1234");
        tels.put("movil", "262626");

        Usuario usuario = new Usuario("123","Carlos","car@mail.com",
                "123","carlos", ciudadServicio.obtenerCiudad(1));
        usuario.setTelefono(tels);

        mockMvc.perform(post("/api/usuario").contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }
}
