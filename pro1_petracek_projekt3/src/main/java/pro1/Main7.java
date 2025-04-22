package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.DeadlineApplicationList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main7 {

    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year)
    {
        String json = Api.getSpecializations(year);
        DeadlineApplicationList deadlineApplicationList = new Gson().fromJson(json, DeadlineApplicationList.class);

        return deadlineApplicationList.items.stream()
                .map(d -> extractNumbersFromString(d.datum.toString()))
                .distinct()
                .sorted(Main7::sortedDatesListString)
                .map(Main7::joinListString)
                .collect(Collectors.joining(","));
    }

    public static List<String> extractNumbersFromString(String input)
    {
        //setup
        List<String> result = new ArrayList<>();
        boolean wasDigit = false;
        StringBuilder temporaryString = new StringBuilder(3);

        //rozebrat string do pole znaků
        for(char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                //vložit číslo (znak) do stringu a nastavit, že minulý znak byl číslo (boolean)
                temporaryString.append(c);
                wasDigit = true;
            } else if (wasDigit) {
                //vložit číslo (string čísla) do pole
                result.add(temporaryString.toString());

                //vyresetovat string a boolean
                temporaryString = new StringBuilder();
                wasDigit = false;
            }
        }

        return result;
    }

    public static String joinListString(List<String> input)
    {
        StringBuilder result = new StringBuilder();

        for(String s: input)
        {
            result.append(s).append(".");
        }

        //odstranit "." na konci
        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    public static Integer sortedDatesListString(List<String> list1, List<String> list2)
    {
        /*
        hodnoty v listech musí mít stejný index, jako v enum, aby představovaly stejný pojmy. Příklad:
        {"14", "4", "2024"}
         */
        enum date
        {
            day,   //index 0
            month, //index 1
            year;  //index 2
        }

        //porovnává, jestli jsou roky stejný
        if(!(list1.get(date.year.ordinal()).equals(list2.get(date.year.ordinal()))))
        {
            //roky nejsou stejný -> porovnává čísla roků
            return Integer.compare(Integer.parseInt(list1.get(date.year.ordinal())),
                    Integer.parseInt(list2.get(date.year.ordinal())));
        }
        //roky jsou stejný -> porovnává, jestli jsou měsíce stejný
        else if(!(list1.get(date.month.ordinal()).equals(list2.get(date.month.ordinal()))))
        {
            //měsíce nejsou stejný -> porovnává čísla měsíců
            return Integer.compare(Integer.parseInt(list1.get(date.month.ordinal())),
                    Integer.parseInt(list2.get(date.month.ordinal())));
        }
        //měsíce jsou stejný -> porovnává čísla dnů
        else
        {
            return Integer.compare(Integer.parseInt(list1.get(date.day.ordinal())),
                    Integer.parseInt(list2.get(date.day.ordinal())));
        }
    }

}