package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Lista enumerada que contiene el tipo de cada lugar
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class TipoLugar implements Serializable {

    //Numero de identificación del tipo de lugar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Nombre del tipo de lugar
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    //Lista de los lugares
    @OneToMany(mappedBy = "tipoLugar")
    private List<Lugar> lugares;

    //Constructor vacío de la clase
    private TipoLugar (){
        super();
    }
    
    /**
    *Constructor completo para crear el tipo de lugar
    *@param Nombre, nombre del tipo de lugar 
    */
    public TipoLugar(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Método que obtiene el código de identificación del tipo del lugar
     * @return el número de identificación
     */
    public int getId() {
        return id;
    }
    /**
     * Método que modifica el código de identificación del tipo de lugar
     * @param id, número nuevo de identificación
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Método que obtiene nombre del tipo de lugar
     * @return nombre de tipo de lugar 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método que modifica el nombre del tipo del lugar
     * @param id, nombre nuevo del tipo de lugar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     /**
     * Método que obtiene la lista de tipos de lugares
     * @return listado de tipos de lugares
     */
    public List<Lugar> getLugares() {
        return lugares;
    }
    /**
     * Método que modifica la lista del tipo de lugar 
     * @param lugares, nueva lista de tipos de lugares
     */
    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }
    /**
     * Metodo que compara dos tipos de lugares
     * @param o, tipo de lugar a comparar 
     * @return true si los tipos de lugares son lo misma, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoLugar tipoLugar = (TipoLugar) o;

        return id == tipoLugar.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
    /**
     * Método que muestra los datos que tiene el tipo del lugar 
     * @return una cadena con los datos del tipo del lugar
     */
    @Override
    public String toString() {
        return "TipoLugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lugares=" + lugares +
                '}';
    }
}
