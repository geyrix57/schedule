package est.una.schedule;

import est.una.schedule.Controller.ConnectionManager;
import est.una.schedule.model.Curso;
import est.una.schedule.model.CursoListWrapper;
import est.una.schedule.model.Grupo;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Casa on 03/06/2015.
 */

@RestController
public class SchedulesWSController {
    @RequestMapping(value = "/allschedule", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<List<String>> schedule(@RequestBody CursoListWrapper cursoList) {
        String[] cursos = new String[cursoList.getCursos().size()];
        for (int i = 0; i < cursoList.getCursos().size(); i++)
            cursos[i] = cursoList.getCursos().get(i);
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator(cursos);

        return scheduleGenerator.getAllPossibleSchedules();
    }

    @RequestMapping(value = "/firstschedule", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<String> fisrtschedule(@RequestBody CursoListWrapper cursoList) {
        String[] cursos = new String[cursoList.getCursos().size()];
        for (int i = 0; i < cursoList.getCursos().size(); i++)
            cursos[i] = cursoList.getCursos().get(i);
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator(cursos);
        return scheduleGenerator.getAllPossibleSchedules().get(0);
    }

    @RequestMapping(value = "/grupo/{nrc}", method = RequestMethod.GET, produces = "application/json")
    public Grupo grupobynrec(@PathVariable String nrc, @RequestHeader HttpHeaders headers) {
        try {
            Grupo g = ConnectionManager.getNewEntityManager().createNamedQuery("Curso.byNRC", Grupo.class).setParameter("nrc", nrc).getSingleResult();
            g.setCursoByCurso(null);
            g.setHorariosByNrc(null);
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return new Grupo();
        }
    }

    @RequestMapping("/clist")
    public List<String> tst(@RequestHeader HttpHeaders headers) {
        try {
            List<String> g = ConnectionManager.getNewEntityManager().createNamedQuery("Curso.cursoid", String.class).getResultList();
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("redirect:" + "index.jsp");
    }
}
