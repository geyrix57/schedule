package est.una.schedule;

import est.una.schedule.model.CursoListWrapper;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Casa on 03/06/2015.
 */

@RestController
public class SchedulesWSController {
    @RequestMapping(value = "/schedule", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String schedule(@RequestBody CursoListWrapper cursoList) {
        for (String s : cursoList.getCursos()) System.out.println(s);
        return "OKAS";
    }
}
