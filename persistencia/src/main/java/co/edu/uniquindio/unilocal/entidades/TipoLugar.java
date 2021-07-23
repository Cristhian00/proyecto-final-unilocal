package co.edu.uniquindio.unilocal.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Lista enumerada que contiene el tipo de cada lugar
 *
 * @author Tatiana Arboleda, Diego Mauricio Valencia y Cristhian Ortiz
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class TipoLugar implements Serializable {

    //Numero de identificación del tipo de lugar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    //Nombre del tipo de lugar
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    //Lista de los lugares
    @OneToMany(mappedBy = "tipoLugar")
    private List<Lugar> lugares;

    /**
     * Constructor completo para crear el tipo de lugar
     *
     * @param nombre, nombre del tipo de lugar
     */
    public TipoLugar(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que muestra los datos que tiene el tipo del lugar
     *
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
