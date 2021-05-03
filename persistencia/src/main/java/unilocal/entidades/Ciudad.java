package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos de una Ciudad
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
public class Ciudad implements Serializable {

    //Número de identificación de una ciudad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    //Nombre de la ciudad
    @Column(name = "nombre", nullable = false)
    private String nombre;

    //Nombre del departamento al cual pertenece
    @ManyToOne
    private Departamento departamento;

    //Lista de las personas que están en la ciudad
    @OneToMany(mappedBy = "ciudadUsuario")
    private List<Usuario> usuarios;

    //Lista de lugares que hay en la ciudad
    @OneToMany(mappedBy = "ciudadLugar")
    private List<Lugar> lugares;

    /**
     *Constructor vacío de la ciudad
    */
    public Ciudad (){
        super();
    }

    /**
     * Constructor completo para crear una Ciudad
     * @param nombre, nombre de la ciudad
     * @param departamento, nombre del departamento al que pertenece la ciudad
     */
    
    public Ciudad(String nombre, Departamento departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }

    /**
     * Método que obtiene el código de identificación de la ciudad
     * @return el número de identificación
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el código de identificación de la ciudad
     * @param id, número nuevo de identificación
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que obtiene el nombre de la ciudad
     * @return el nombre de la ciudad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el nombre de la ciudad
     * @param nombre, nombre nuevo de la ciudad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el nombre del departamento al que pertenece la ciudad
     * @return el nombre del departamento correspondiente
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Método que modifica el nombre del departamento al que pertenece la ciudad
     * @param departamento, nombre nuevo del departamento al que pertenece la ciudad
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Metodo que compara dos ciudades
     * @param o, ciudad a comparar
     * @return true si las ciudades son la misma, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciudad ciudad = (Ciudad) o;

        return id == ciudad.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Método que muestra los datos que tiene la ciudad
     * @return una cadena con los datos de la ciudad
     */
    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", departamento=" + departamento.getNombre() + '\'' +
                ", pais=" + departamento.getPais() +
                '}';
    }
}
