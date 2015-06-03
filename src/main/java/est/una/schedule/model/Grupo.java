package est.una.schedule.model;

import javax.persistence.*;

/**
 * Created by geykel on 02/06/2015.
 */
@Entity
@Table(name = "grupo", schema = "", catalog = "schedules")
@NamedQueries({
        @NamedQuery(name = "Curso.findNRC", query = "select grupo.nrc from Grupo grupo where grupo.cursoByCurso.codigo = :curso"),
        @NamedQuery(name = "Curso.getGroups", query = "select grupo from Grupo grupo where grupo.cursoByCurso.codigo = :curso")
})
public class Grupo {
    private String nrc;
    private String campus;
    private int restante;
    private String instructor;
    private Curso cursoByCurso;
    private Horario horarioByNrc;

    @Id
    @Column(name = "nrc")
    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    @Basic
    @Column(name = "campus")
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @Basic
    @Column(name = "restante")
    public int getRestante() {
        return restante;
    }

    public void setRestante(int restante) {
        this.restante = restante;
    }

    @Basic
    @Column(name = "instructor")
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo = (Grupo) o;

        if (restante != grupo.restante) return false;
        if (nrc != null ? !nrc.equals(grupo.nrc) : grupo.nrc != null) return false;
        if (campus != null ? !campus.equals(grupo.campus) : grupo.campus != null) return false;
        if (instructor != null ? !instructor.equals(grupo.instructor) : grupo.instructor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nrc != null ? nrc.hashCode() : 0;
        result = 31 * result + (campus != null ? campus.hashCode() : 0);
        result = 31 * result + restante;
        result = 31 * result + (instructor != null ? instructor.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    public Curso getCursoByCurso() {
        return cursoByCurso;
    }

    public void setCursoByCurso(Curso cursoByCurso) {
        this.cursoByCurso = cursoByCurso;
    }

    @OneToOne(mappedBy = "grupoByNrcGrupo")
    public Horario getHorarioByNrc() {
        return horarioByNrc;
    }

    public void setHorarioByNrc(Horario horarioByNrc) {
        this.horarioByNrc = horarioByNrc;
    }
}
