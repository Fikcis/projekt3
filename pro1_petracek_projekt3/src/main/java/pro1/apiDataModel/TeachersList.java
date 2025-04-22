package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TeachersList
{
    @SerializedName("ucitel")
    public List<Teacher> items;
}
