package est.una.schedule;

import est.una.schedule.model.Curso;
import est.una.schedule.model.CursoListWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Casa on 03/06/2015.
 */

@RestController
public class SchedulesWSController {
    @RequestMapping(value = "/schedule", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Curso schedule(@RequestBody CursoListWrapper cursoList) {

        String[] cursos = new String[cursoList.getCursos().size()];
        for (int i = 0; i < cursoList.getCursos().size(); i++)
            cursos[i] = cursoList.getCursos().get(i);
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator(cursos);

        Curso c = new Curso();
        c.setCodigo("EIF203");
        return c;

    }
}
