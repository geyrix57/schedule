package est.una.schedule.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by geykel on 03/06/2015.
 */
@Entity
@Table(name = "curso", schema = "", catalog = "schedules")
public class Curso implements Serializable {
    private String codigo;
    private String titulo;
    private Collection<Grupo> gruposByCodigo;

    @Id
    @Column(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curso curso = (Curso) o;

        if (codigo != null ? !codigo.equals(curso.codigo) : curso.codigo != null) return false;
        if (titulo != null ? !titulo.equals(curso.titulo) : curso.titulo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        return result;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoByCurso")
    public Collection<Grupo> getGruposByCodigo() {
        return gruposByCodigo;
    }

    public void setGruposByCodigo(Collection<Grupo> gruposByCodigo) {
        this.gruposByCodigo = gruposByCodigo;
    }
}
