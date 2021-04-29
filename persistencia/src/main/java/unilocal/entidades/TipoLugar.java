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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "tipoLugar")
    private List<Lugar> lugares;

    private TipoLugar (){
        super();
    }

    public TipoLugar(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

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

    @Override
    public String toString() {
        return "TipoLugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lugares=" + lugares +
                '}';
    }
}
