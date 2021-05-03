package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos de un Departamento
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Departamento implements Serializable {

    //Código de identificación de un departamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Nombre del departamento
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    //Nombre del país al que pertenece el departamento
    @Column(name = "pais", nullable = false)
    private String pais;

    //Lista de ciudades que contiene el departamentos
    @OneToMany(mappedBy = "departamento")
    private List<Ciudad> ciudades;

    //Constructor vacío de Departamento
    public Departamento (){
        super();
    }

    /**
     * Constructor completo para crear un departamento
     * @param nombre, nombre del departameno
     * @param pais, nombre del país al que pertenece el departamento
     */
    public Departamento(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    /**
     * Método que obtiene el numero de identificación del departamento
     * @return el código de identificación
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el código de identificación del departamento
     * @param id, número nuevo de identificación
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que obtiene el nombre del departamento
     * @return el nombre del departamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el el nombre del departamento
     * @param nombre, nombre nuevo del departamento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el nombre del país al que pertenece el departamento
     * @return el nombre del pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Método que modifica el nombre del país al que pertenece el departamento
     * @param pais, nombre nuevo del pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Método que obtiene la lista de las ciudades que pertenecen al departamento 
     * @return la lista de ciudades
     */
    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    /**
     * Método que modifica la lista de las ciudades que pertenecen al departamento
     * @param ciudades, lista nueva de ciudades
     */
    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     * Metodo que compara dos departamentos
     * @param o, departamento a comparar
     * @return true si los departamentos son el mismo, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departamento that = (Departamento) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

     /**
     * Método que muestra los datos que tiene el departamento
     * @return una cadena con los datos del deprtamento
     */
    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais +
                '}';
    }
}
