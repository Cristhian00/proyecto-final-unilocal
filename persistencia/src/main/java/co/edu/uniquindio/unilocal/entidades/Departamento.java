package co.edu.uniquindio.unilocal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    @NotBlank(message = "Debe ingresar el nombre del departamento")
    @Size(min = 2, max = 100, message = "El nombre del departamento debe tener entre 2 y 100 caracteres")
    private String nombre;

    //Nombre del país al que pertenece el departamento
    @Column(name = "pais", length = 100, nullable = false)
    @NotBlank(message = "Debe ingresar el nombre del pais")
    @Size(min = 2, max = 100, message = "El nombre del pais debe tener entre 2 y 100 caracteres")
    private String pais;

    //Lista de ciudades que contiene el departamentos
    @OneToMany(mappedBy = "departamento")
    @JsonIgnore
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
