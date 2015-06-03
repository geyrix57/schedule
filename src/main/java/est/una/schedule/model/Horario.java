package est.una.schedule.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by geykel on 03/06/2015.
 */
@Entity
@Table(name = "horario", schema = "", catalog = "schedules")
public class Horario {
    private Integer dia;
    private Timestamp horaInicio;
    private Timestamp horaFinal;
    private Integer totalHoras;
    private int codigo;
    private Grupo grupoByNrcGrupo;

    @Basic
    @Column(name = "dia")
    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    @Basic
    @Column(name = "hora_inicio")
    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Basic
    @Column(name = "hora_final")
    public Timestamp getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Timestamp horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Basic
    @Column(name = "total_horas")
    public Integer getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Integer totalHoras) {
        this.totalHoras = totalHoras;
    }

    @Id
    @Column(name = "codigo")
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        if (codigo != horario.codigo) return false;
        if (dia != null ? !dia.equals(horario.dia) : horario.dia != null) return false;
        if (horaInicio != null ? !horaInicio.equals(horario.horaInicio) : horario.horaInicio != null) return false;
        if (horaFinal != null ? !horaFinal.equals(horario.horaFinal) : horario.horaFinal != null) return false;
        if (totalHoras != null ? !totalHoras.equals(horario.totalHoras) : horario.totalHoras != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dia != null ? dia.hashCode() : 0;
        result = 31 * result + (horaInicio != null ? horaInicio.hashCode() : 0);
        result = 31 * result + (horaFinal != null ? horaFinal.hashCode() : 0);
        result = 31 * result + (totalHoras != null ? totalHoras.hashCode() : 0);
        result = 31 * result + codigo;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "nrc_grupo", referencedColumnName = "nrc", nullable = false, insertable = false, updatable = false)
    public Grupo getGrupoByNrcGrupo() {
        return grupoByNrcGrupo;
    }

    public void setGrupoByNrcGrupo(Grupo grupoByNrcGrupo) {
        this.grupoByNrcGrupo = grupoByNrcGrupo;
    }
}
