import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "4997922448";
        String query = "Select s.course_id, c.name, \n" +
                "COUNT(s.course_id) / count(Distinct MONTH(s.subscription_date)) as count_buy \n" +
                "From Subscriptions as s JOIN Courses as c ON c.id = s.course_id \n" +
                "GROUP BY s.course_id  ORDER BY course_id";
        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("name");
                double countBuy = resultSet.getDouble("count_buy");
                System.out.println(String.format("Количество покупок в месяц  курса : %s равно %.4f ", courseName, countBuy));
            }
            connection.close();

        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }

    }
}
