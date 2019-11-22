import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        studentList = (List<Student>)session.createQuery("from Student").list();
        studentList.forEach(System.out::println);
        session.close();
    }
}
