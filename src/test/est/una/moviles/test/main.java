package est.una.moviles.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import est.una.schedule.ScheduleGenerator;
import est.una.schedule.model.CursoListWrapper;

/**
 * Created by Casa on 03/06/2015.
 */
public class main {
    public static void main(String[] args) throws JsonProcessingException {
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator("EIF203", "EIF403");
        CursoListWrapper cursoListWrapper = new CursoListWrapper(true);
        cursoListWrapper.getCursos().add("1");
        cursoListWrapper.getCursos().add("2");
        cursoListWrapper.getCursos().add("3");
        cursoListWrapper.getCursos().add("4");
        ObjectMapper mapper = new ObjectMapper();
        System.out.print(mapper.writeValueAsString(cursoListWrapper));
    }
}
