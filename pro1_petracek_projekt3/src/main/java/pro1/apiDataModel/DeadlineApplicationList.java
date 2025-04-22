package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DeadlineApplicationList {
    @SerializedName("prijimaciObor")
    public List<DeadlineApplication> items;
}
