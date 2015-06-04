package est.una.schedule.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by geykel on 03/06/2015.
 */
@Entity
@Table(name = "horario", schema = "", catalog = "schedules")
public class Horario implements Serializable {
    private int codigo;
    private Integer dia;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private Integer totalHoras;
    private Grupo grupoByNrcGrupo;

    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "hora_inicio")
    @Convert(converter = est.una.schedule.model.LocalTimeConverter.class)
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Basic
    @Column(name = "hora_final")
    @Convert(converter = est.una.schedule.model.LocalTimeConverter.class)
    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Basic
    @Column(name = "dia")
    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    @Basic
    @Column(name = "total_horas")
    public Integer getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Integer totalHoras) {
        this.totalHoras = totalHoras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        if (!dia.equals(horario.dia)) return false;
        if (!horaInicio.equals(horario.horaInicio)) return false;
        if (!horaFinal.equals(horario.horaFinal)) return false;
        if (!totalHoras.equals(horario.totalHoras)) return false;
        return grupoByNrcGrupo.equals(horario.grupoByNrcGrupo);

    }

    @Override
    public int hashCode() {
        int result = dia.hashCode();
        result = 31 * result + horaInicio.hashCode();
        result = 31 * result + horaFinal.hashCode();
        result = 31 * result + totalHoras.hashCode();
        result = 31 * result + grupoByNrcGrupo.hashCode();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "nrc_grupo", referencedColumnName = "nrc", nullable = false)
    public Grupo getGrupoByNrcGrupo() {
        return grupoByNrcGrupo;
    }

    public void setGrupoByNrcGrupo(Grupo grupoByNrcGrupo) {
        this.grupoByNrcGrupo = grupoByNrcGrupo;
    }
}
