package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los atributos de una Ciudad
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Ciudad implements Serializable {

    //Número de identificación de una ciudad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
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
     * Constructor completo para crear una Ciudad
     *
     * @param nombre,       nombre de la ciudad
     * @param departamento, nombre del departamento al que pertenece la ciudad
     */

    public Ciudad(String nombre, Departamento departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }

    /**
     * Método que muestra los datos que tiene la ciudad
     *
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
