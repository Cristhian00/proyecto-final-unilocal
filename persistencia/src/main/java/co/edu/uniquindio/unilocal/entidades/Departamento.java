package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos de un Departamento
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class Departamento implements Serializable {

    //Código de identificación de un departamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
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

    /**
     * Constructor completo para crear un departamento
     *
     * @param nombre, nombre del departameno
     * @param pais,   nombre del país al que pertenece el departamento
     */
    public Departamento(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    /**
     * Método que muestra los datos que tiene el departamento
     *
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
