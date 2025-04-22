package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.TeachersList;
import java.util.Comparator;
import java.util.HashMap;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID

        String json_teachers = Api.getTeachersByDepartment(department);

        TeachersList teachers = new Gson().fromJson(json_teachers, TeachersList.class);

        HashMap<Long,Integer> hashmap = new HashMap<>();
        for(var teacher :teachers.items)
        {
            Integer current =hashmap.getOrDefault(teacher.id,0);
            hashmap.put(teacher.id, current + teacher.personCount);
        }
        return hashmap.entrySet().stream()
                .max(Comparator.comparing(p->p.getValue()))
                .get()
                .getKey();
    }
}