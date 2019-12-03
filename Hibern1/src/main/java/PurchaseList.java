import org.hibernate.metamodel.model.domain.internal.AbstractIdentifiableType;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@IdClass(PurchaseList.PurchaseKey.class)
public class PurchaseList  {

    @Id
    @Column(name = "student_name")
    private String studentName;
    @Id
    @Column(name = "course_name")
    private String courseName;

    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseList() {
        super();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, стоимость: %в, дата регистрации: ", studentName, courseName,
                price, new SimpleDateFormat("dd.MM.yyyy").format(subscriptionDate));
    }


    public class PurchaseKey implements Serializable {

        static final long serialVersionUID = 1L;

        private String studentName;
        private String courseName;

        public PurchaseKey() {
            super();
        }

        public PurchaseKey(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

}
