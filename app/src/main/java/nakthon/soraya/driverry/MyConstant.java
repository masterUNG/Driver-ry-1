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
    private String[] jobStrings = new String[]{
            "id",
            "ID_passenger",
            "ID_driver",
            "Status",
            "Date",
            "Time",
            "NameStart",
            "Lat_start",
            "Lng_start",
            "NameEnd",
            "Lat_end",
            "Lng_end"};

    private String urlGetPassengerWhereID = "http://swiftcodingthai.com/ry/get_passenger_where_id.php";

    private String urlGetJobWhereID = "http://swiftcodingthai.com/ry/get_job_where_idPass_and_Status.php";

    public String getUrlGetPassengerWhereID() {
        return urlGetPassengerWhereID;
    }

    public String[] getJobStrings() {
        return jobStrings;
    }

    public String getUrlGetJobWhereID() {
        return urlGetJobWhereID;
    }

        public String[] getUserStrings() {
        return userStrings;
    }
} // Main Class
