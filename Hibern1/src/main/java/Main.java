import org.hibernate.Session;
import org.hibernate.query.Query;
import org.w3c.dom.ls.LSOutput;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Session session = Util.getSessionFactory().openSession();

        session.beginTransaction();

        String hql = "Select new Link(c, s) From Course c, Student s, PurchaseList p Where c.name = p.purchaseKey.courseName and " +
                "s.name = p.purchaseKey.studentName";
        Query query = session.createQuery(hql);

        List<Link> linkList = query.getResultList();
        linkList.forEach(ls -> session.persist(ls));

        System.out.println("Сохранено " + linkList.size() + " записей");
        session.getTransaction().commit();
        session.close();
    }
}
