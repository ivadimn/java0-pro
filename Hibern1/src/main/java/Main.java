import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        List<Course> coursesList = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
       // studentList = (List<Student>)session.createQuery("from Student").list();
        //studentList.forEach(System.out::println);
        //coursesList = (List<Course>) session.createQuery("from Course").list();
        //System.out.println("***************************************************");
        //coursesList.forEach(c -> System.out.println(c.getName()));
        Course course = session.get(Course.class, 1);
        System.out.println(course.getTeacher().getName());
        Student student = session.get(Student.class, 1);
        System.out.println(student);
        student.getCourseList().forEach(c -> System.out.println("\t" + c.getName()));

        session.close();
    }
}
