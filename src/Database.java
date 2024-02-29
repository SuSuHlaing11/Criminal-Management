import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public Connection databaseLink;

    public Connection getDBConnection() {
        String url = "jdbc:mysql://localhost:3306/crime_manage";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, user, password);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

    public static void insertData(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'insertData'");
    }
}
