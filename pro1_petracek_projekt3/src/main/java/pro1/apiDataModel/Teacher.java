package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Teacher
{
    @SerializedName("ucitIdno")
    public long id;
    @SerializedName("jmeno")
    public String firstName;
    @SerializedName("prijmeni")
    public String surname;
    @SerializedName("email")
    public String  email;
    @SerializedName("personCount")
    public int  personCount;
}
