package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main5 {

    public static void main(String[] args) {
        System.out.println(roomsSummary("KIKM",2024));
    }

    public static String roomsSummary(String department, int year)
    {
        // TODO 5.1: Vrať výpis učeben, které katedra v daném roce využila (seřadit abecedně, oddělit čárkou)

        String json_department = Api.getActionsByDepartment(department,year);
        ActionsList actions = new Gson().fromJson(json_department, ActionsList.class);

        return actions.items.stream()
                .map(a -> a.room)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
    }
}