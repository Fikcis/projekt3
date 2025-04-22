package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.TeachersList;
import java.util.Comparator;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres

        String json_teachers = Api.getTeachersByDepartment(department);
        TeachersList teachers = new Gson().fromJson(json_teachers, TeachersList.class);

        teachers.items.stream()
                .filter(item -> item.email != null)
                .sorted(Comparator.comparing(item -> item.email.length()))
                .limit(count)
                .toList()
                .forEach(item -> System.out.println(item.email));

    }
}