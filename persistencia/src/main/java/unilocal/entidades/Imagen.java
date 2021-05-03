package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Imagen implements Serializable {

    //Número de identificación de una imagen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Dirección de la url a la que pertenece la imagen
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    //Lugar el cual la imagen está describiendo
    @ManyToOne
    private Lugar lugar;

     /**
     * Constructor vacio del horario
     */
    public Imagen (){
        super();
    }
    
     /**
     * Constructor completo para crear una imagen
     * @param url, url de donde sale la imagen
     * @param lugar, lugar al que la imagen pertenece
     */
    public Imagen(String url, Lugar lugar) {
        this.url = url;
        this.lugar = lugar;
    }

    /**
     * Método que obtiene el numero de identificacion de la imagen
     * @return el numero de identificación
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el código de identificación del horario
     * @param codigo, número nuevo de identificación
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que obtiene el código de identificación del horario
     * @return el código de identificación
     */
    public String getUrl() {
        return url;
    }

    /**
     * Método que modifica el código de identificación del horario
     * @param codigo, número nuevo de identificación
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Método que obtiene el código de identificación del horario
     * @return el código de identificación
     */
    public Lugar getLugar() {
        return lugar;
    }

    /**
     * Método que modifica el código de identificación del horario
     * @param codigo, número nuevo de identificación
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagen imagen = (Imagen) o;

        return id == imagen.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", lugar=" + lugar.getNombre() +
                '}';
    }
}
