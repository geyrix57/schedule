package est.una.schedule.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Casa on 03/06/2015.
 */
public class CursoListWrapper {
    private List<String> cursos;

    public CursoListWrapper() {
    }

    public CursoListWrapper(boolean b) {
        cursos = new ArrayList<>();
    }

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }
}
