package est.una.schedule;

import est.una.schedule.Controller.ConnectionManager;
import est.una.schedule.model.Grupo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by geykel on 02/06/2015.
 */
public class ScheduleGenerator {

    private String[][] nrc;
    private int[] counters;
    private ConcurrentMap<String, Grupo> grupos;
    private final EntityManager em;

    public ScheduleGenerator(String... Cursos) {
        em = ConnectionManager.getNewEntityManager();
        nrc = new String[Cursos.length][];
        counters = new int[Cursos.length];
        grupos = new ConcurrentHashMap<>();
        for (int i = 0; i < Cursos.length; i++) {
            nrc[i] = em.createNamedQuery("Curso.getGroups",Grupo.class).setParameter("curso", Cursos[i]).getResultList().stream().parallel().map(grupo -> {
                grupos.putIfAbsent(grupo.getNrc(), grupo);
                return grupo.getNrc();
            }).toArray(String[]::new);
            //nrc[i] = (String[]) em.createNamedQuery("Curso.findNRC").setParameter("curso", Cursos[i]).getResultList().toArray();
        }
    }

    public ScheduleGenerator(String[]... Cursos) {
        em = null;
        nrc = Cursos;
        counters = new int[Cursos.length];
    }

    public List<List<String>> getAllPossibleSchedules() {
        List<List<String>> schedules = new ArrayList<>();
        do {
            schedules.add(getSchedule());
        } while (increment());
        return schedules;
    }

    private boolean increment() {
        for (int i = counters.length - 1; i >= 0; i--) {
            if (counters[i] < nrc[i].length - 1) {
                counters[i]++;
                return true;
            } else {
                counters[i] = 0;
            }
        }
        return false;
    }

    private List<String> getSchedule() {
        List<String> schedule = new ArrayList<>();
        for (int i = 0; i < counters.length; i++) {
            schedule.add(nrc[i][counters[i]]);
        }
        return schedule;
    }

    public static void main(String[] args) {
        /*String[] set1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] set2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        String[] set3 = {"$", "%", "£", "!", "^", "#", "-", "*"};
        String[] set4 = {"k", "l"};
        String[] set5 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] set6 = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        String[] set7 = {"$", "%", "£", "!", "^", "#", "-", "*"};
        String[] set8 = {"k", "l"};
        ScheduleGenerator sg = new ScheduleGenerator(set1, set2, set3, set4, set5, set6, set7, set8);
        System.out.println(sg.getAllPossibleSchedules().size());*/
        ScheduleGenerator sg = new ScheduleGenerator("EIF203");
        System.out.println(sg.getAllPossibleSchedules());
    }
}

