import org.hibernate.Session;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //List<Student> studentList;
        //List<Course> coursesList;
        Session session = Util.getSessionFactory().openSession();
        /*studentList = (List<Student>)session.createQuery("from Student").list();
        studentList.forEach(System.out::println);

        System.out.println("***************************************************");
        coursesList = (List<Course>) session.createQuery("from Course").list();
        coursesList.forEach(c ->
            {
                System.out.println(c.getName());
                System.out.println("Список студентов");
                c.getStudentList().forEach(s -> System.out.println("\t" + s.getName()));
            });

        System.out.println("***************************************************");
        Course course = session.get(Course.class, 1);
        System.out.println(course.getTeacher().getName());
        Student student = session.get(Student.class, 1);
        System.out.println(student);
        student.getCourseList().forEach(c -> System.out.println("\t" + c.getName()));

        System.out.println("PurchaseList ***************************************************");
        List<PurchaseList> purchaseList = session.createQuery("from PurchaseList").list();
        purchaseList.forEach(System.out::println);

        System.out.println("***************************************************");
        List<Subscription> subscriptionList = session.createQuery("from Subscription").list();
        subscriptionList.forEach(System.out::println); */

        Student s = session.get(Student.class, 1);
        Course c = session.get(Course.class, 2);
        System.out.println(s.getName());
        System.out.println(c.getName());

        session.close();
    }
}
