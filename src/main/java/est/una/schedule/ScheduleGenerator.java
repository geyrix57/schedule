package est.una.schedule;

import est.una.schedule.Controller.ConnectionManager;
import est.una.schedule.model.Grupo;
import est.una.schedule.model.Horario;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
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
            nrc[i] = em.createNamedQuery("Curso.getGroups", Grupo.class).setParameter("curso", Cursos[i]).getResultList().stream().parallel().map(grupo -> {
                grupos.putIfAbsent(grupo.getNrc(), grupo);
                return grupo.getNrc();
            }).toArray(String[]::new);
        }
    }

    public ScheduleGenerator(String[]... Cursos) {
        em = null;
        nrc = Cursos;
        counters = new int[Cursos.length];
    }

    public List<List<String>> getAllPossibleSchedules() {
        List<List<String>> schedules = new ArrayList<>();
        List<String> temp;
        do {
            temp = getSchedule();
            if (validateSchedule(temp))
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

    private boolean validateSchedule(List<String> list) {
        List<Horario>[] dias = new ArrayList[7];
        List<Horario> diaTemp;
        for (String nrc : list) {
            for (Horario h1 : grupos.get(nrc).getHorariosByNrc()) {
                diaTemp = dias[h1.getDia()];
                if (diaTemp == null) {
                    diaTemp = new ArrayList<>();
                } else {
                    for (Horario h2 : diaTemp) {
                        if (!(h1.getHoraFinal().isBefore(h2.getHoraInicio()) || h1.getHoraInicio().isAfter(h2.getHoraFinal())))
                            return false;
                    }
                }
                diaTemp.add(h1);
                dias[h1.getDia()] = diaTemp;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        ScheduleGenerator sg = new ScheduleGenerator("EIF403", "EIF203");
        System.out.println(sg.getAllPossibleSchedules());

        //Agregar curso
        /*Curso curso = new Curso();
        curso.setCodigo("EIF403");
        curso.setTitulo("El titulo");
        curso.setGruposByCodigo(new ArrayList<>());

        for(int i=0; i<4; i++) {
            Grupo g = new Grupo();
            g.setCampus("NA");
            g.setCursoByCurso(curso);
            g.setInstructor("Alguien" + i);
            g.setNrc(Integer.toString(i));
            g.setRestante(i * 10);
            g.setHorariosByNrc(new ArrayList<>());
            for(int j=0; j<2; j++) {
                Horario h = new Horario();
                h.setDia((i * (j + 14)) % 7);
                h.setHoraInicio(LocalTime.of(i * j + 10, 30));
                h.setHoraFinal(LocalTime.of(i * j + 12, 30));
                h.setGrupoByNrcGrupo(g);
                if(!g.getHorariosByNrc().contains(h))
                    g.getHorariosByNrc().add(h);
            }
            curso.getGruposByCodigo().add(g);
        }
        EntityManager em = ConnectionManager.getNewEntityManager();
        em.getTransaction().begin();

        em.persist(curso);

        em.getTransaction().commit();
        em.close();
        ConnectionManager.close();*/
    }
}

