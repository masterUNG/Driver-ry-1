package nakthon.soraya.driverry;

/**
 * Created by DARK on 4/10/2559.
 */

public class MyConstant {

    //Explicit
    private String[] userStrings = new String[]{
            "id",
            "User",
            "Password",
            "Name",
            "Surname",
            "Address",
            "Phone",
            "Id_car"};

    private String urlGetJobWhereID = "http://swiftcodingthai.com/ry/get_job_where_idPass_and_Status.php";

    public String getUrlGetJobWhereID() {
        return urlGetJobWhereID;
    }

    public String[] getUserStrings() {
        return userStrings;
    }
} // Main Class
