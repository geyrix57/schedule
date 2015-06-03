package est.una.schedule.model;

import javax.persistence.*;

/**
 * Created by geykel on 02/06/2015.
 */
@Entity
@Table(name = "horario_escojido", schema = "", catalog = "schedules")
@IdClass(HorarioEscojidoPK.class)
public class HorarioEscojido {
    private int horarioEscojido;
    private int cursos;
    private Estudiante estudianteByIdEstudiante;

    @Id
    @Column(name = "horario_escojido")
    public int getHorarioEscojido() {
        return horarioEscojido;
    }

    public void setHorarioEscojido(int horarioEscojido) {
        this.horarioEscojido = horarioEscojido;
    }

    @Id
    @Column(name = "cursos")
    public int getCursos() {
        return cursos;
    }

    public void setCursos(int cursos) {
        this.cursos = cursos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorarioEscojido that = (HorarioEscojido) o;

        if (horarioEscojido != that.horarioEscojido) return false;
        if (cursos != that.cursos) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = horarioEscojido;
        result = 31 * result + cursos;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante", nullable = false, insertable = false, updatable = false)
    public Estudiante getEstudianteByIdEstudiante() {
        return estudianteByIdEstudiante;
    }

    public void setEstudianteByIdEstudiante(Estudiante estudianteByIdEstudiante) {
        this.estudianteByIdEstudiante = estudianteByIdEstudiante;
    }
}
