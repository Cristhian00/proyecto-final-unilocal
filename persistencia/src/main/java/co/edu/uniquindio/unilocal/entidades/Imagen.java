package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Imagen implements Serializable {

    //Número de identificación de una imagen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    //Dirección de la url a la que pertenece la imagen
    @Column(name = "url", nullable = false, unique = true)
    @NotBlank(message = "Debe ingresar la url de la imagen")
    @Size(max = 255, message = "La url no puede tener más de 255 caracteres")
    private String url;

    //Lugar el cual la imagen está describiendo
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lugar lugar;

    /**
     * Constructor completo para crear una imagen
     *
     * @param url,   url de donde sale la imagen
     * @param lugar, lugar al que la imagen pertenece
     */
    public Imagen(String url, Lugar lugar) {
        this.url = url;
        this.lugar = lugar;
    }

    /**
     * Método que muestra los datos que tiene la imagen
     *
     * @return una cadena con los datos de la imagen
     */
    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", lugar=" + lugar.getNombre() +
                '}';
    }
}
