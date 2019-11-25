import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        List<Course> coursesList = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        studentList = (List<Student>)session.createQuery("from Student").list();
        studentList.forEach(System.out::println);
        coursesList = (List<Course>) session.createQuery("from Course").list();
        System.out.println("***************************************************");
        coursesList.forEach(c -> System.out.println(c.getName()));

        session.close();
    }
}
