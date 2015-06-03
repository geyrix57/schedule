package est.una.schedule.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 02/06/2015.
 */
public class HorarioEscojidoPK implements Serializable {
    private int horarioEscojido;
    private int cursos;

    @Column(name = "horario_escojido")
    @Id
    public int getHorarioEscojido() {
        return horarioEscojido;
    }

    public void setHorarioEscojido(int horarioEscojido) {
        this.horarioEscojido = horarioEscojido;
    }

    @Column(name = "cursos")
    @Id
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

        HorarioEscojidoPK that = (HorarioEscojidoPK) o;

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
}
