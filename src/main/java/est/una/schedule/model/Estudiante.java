package est.una.schedule.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 02/06/2015.
 */
@Entity
@Table(name = "estudiante", schema = "", catalog = "schedules")
public class Estudiante {
    private int idEstudiante;
    private String pass;
    private Collection<HorarioEscojido> horarioEscojidosByIdEstudiante;

    @Id
    @Column(name = "id_estudiante")
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estudiante that = (Estudiante) o;

        if (idEstudiante != that.idEstudiante) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstudiante;
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "estudianteByIdEstudiante")
    public Collection<HorarioEscojido> getHorarioEscojidosByIdEstudiante() {
        return horarioEscojidosByIdEstudiante;
    }

    public void setHorarioEscojidosByIdEstudiante(Collection<HorarioEscojido> horarioEscojidosByIdEstudiante) {
        this.horarioEscojidosByIdEstudiante = horarioEscojidosByIdEstudiante;
    }
}
