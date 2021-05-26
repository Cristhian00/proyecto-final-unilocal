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
     * Método que modifica el código de identificación de la imagen
     * @param id, número nuevo de identificación de la imagen
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que obtiene la url de la imagen
     * @return url de la imagen
     */
    public String getUrl() {
        return url;
    }

    /**
     * Método que modifica la url de la imagen
     * @param url, url nueva de la imagen
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Método que obtiene el lugar de donde es la imagen
     * @return el lugar de donde es la imagen
     */
    public Lugar getLugar() {
        return lugar;
    }

    /**
     * Método que modifica el lugar de donde pertenece la imagen
     * @param lugar, lugar nuevo de la imagen
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    
    /**
     * Metodo que compara dos imagenes
     * @param o, imagen a comparar
     * @return true si las imagenes son la misma, de lo contrario false
     */
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
     /**
     * Método que muestra los datos que tiene la imagen
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
