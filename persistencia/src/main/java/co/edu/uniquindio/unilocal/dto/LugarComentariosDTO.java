package co.edu.uniquindio.unilocal.dto;

import co.edu.uniquindio.unilocal.entidades.Comentario;
import co.edu.uniquindio.unilocal.entidades.Lugar;

public class LugarComentariosDTO {

    private Lugar lugar;
    private Comentario comentario;

    public LugarComentariosDTO(Lugar lugar, Comentario comentarios) {
        this.lugar = lugar;
        this.comentario = comentarios;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
