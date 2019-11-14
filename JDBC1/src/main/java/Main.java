import java.sql.*;

public class Main {

    public static void main(String[] args) {
        //String url = "jdbc:mysql://localhost:3306/learn?useUnicode=true&useJDBCCompliantTimezoneShift=true"
        //        +"&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/learn";
        String user = "vadim";
        String pass = "4997922448";
        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select name From department");
            while (resultSet.next()) {
                String depName = resultSet.getString("name");
                System.out.println(depName);
            }
            connection.close();

        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }

    }
}
