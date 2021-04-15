package unilocal.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "dia", nullable = false, length = 20)
    private DiaSemana dia;

    @Column(name = "hora_apertura", length = 10, nullable = false)
    private String horaApertura;

    @Column(name = "hora_cierre", length = 10, nullable = false)
    private String horaCierre;

    @ManyToMany(mappedBy = "horarios")
    private List<Lugar> lugares;

    public Horario (){
        super();
    }

    public Horario(DiaSemana dia, String horaApertura, String horaCierre) {
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        return codigo == horario.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
