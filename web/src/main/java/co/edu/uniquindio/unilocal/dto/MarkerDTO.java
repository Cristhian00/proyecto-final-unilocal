package co.edu.uniquindio.unilocal.dto;

import co.edu.uniquindio.unilocal.entidades.TipoLugar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class MarkerDTO {

    private int id;
    private String nombre;
    private TipoLugar tipoLugar;
    private String descripcion;
    private float lat, lng;

}
